from urllib import request
import json
import pymongo
import re
import time

# 获取mongo连接
uri = 'mongodb://localhost:27017'
client = pymongo.MongoClient(uri)
db = client.yc74ibike
collection = db.comment
coll = db.covid19

def open_url(url):
    head = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0"}

    req = request.Request(url, headers=head)
    response = request.urlopen(req)
    html = response.read()
    return html

def getResponse(url):
    data = open_url(url).decode('utf-8')
    return data


if __name__ == '__main__':
    # 调用百度国内疫情数据接口
    url = 'https://voice.baidu.com/newpneumonia/get?target=trend&isCaseIn=0&stage=publish'
    result = getResponse(url)
    # 数据清洗
    result = json.loads(result, encoding='utf-8')
    # print(result)
    data = result['data']
    # print(data)
    insert_mongo_data_list = []
    for item in data:
        insert_data_one = {}   # 当前城市数据
        item_data_list = item['trend']['list']
        insert_data_one['name'] = item['name']   # 城市名字
        insert_data_one['define'] = item_data_list[0]['data'][-1]   # 确诊数据
        insert_data_one['cure'] = item_data_list[1]['data'][-1]    # 治愈数
        insert_data_one['death'] = item_data_list[2]['data'][-1]   # 死亡数
        insert_data_one['new'] = item_data_list[3]['data'][-1]    # 新增数
        # 现存数
        insert_data_one['now'] = insert_data_one['define'] - insert_data_one['cure'] - insert_data_one['death']
        insert_data_one['date'] = item['trend']['updateDate'][-1]   # 数据更新时间
        insert_mongo_data_list.append(insert_data_one)

    insert_mongo_data = {}
    # 数据插入时间
    insert_mongo_data['date'] = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
    # 插入数据
    insert_mongo_data['list'] = insert_mongo_data_list
    print(insert_mongo_data)
    coll.insert_one(insert_mongo_data)
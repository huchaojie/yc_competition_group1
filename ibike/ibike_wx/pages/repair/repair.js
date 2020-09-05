// pages/repair/repair.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bikeNo:'',
    types:[]
  },
// 勾选故障类型，获取类型值存入checkboxValue
  checkboxChange: function (e) {
    var values = e.detail.value;   //当前选的那个值
    console.log("选择的故障:"+values);
    this.setData({
      types: values
    })
  },
  //扫码
  scanCode:function(e){
    var that=this;
 //扫码功能
     //用软件生成一个测试二维码: www.liantu.com
     // 比如:  http://localhost:8080/00000001
     //请注意:上线后，微信要求访问的协议必须是 https
     //且不能有端口号( 即https 443)
     wx.scanCode({
      success:function(r){
        var code=r.result;
        console.log( "要报修的单车码:"+code );
        that.setData({
          bikeNo:code
        })
      }
    });
  },
// 提交到服务器
   formSubmit: function (e) {   //表单事件对象
    var that=this;
    var bikeNo = e.detail.value.bikeNo;
    //也可以
    //var bikeNo=this.data.bikeNo;
    var types = this.data.types;
    var globalData = getApp().globalData;
    var phoneNum = globalData.phoneNum;
    var openId=wx.getStorageSync('openId');
    wx.getLocation({
      success:function(res){
        var latitude=res.latitude;
        var longitude=res.longitude;
        //1.向业务系统发生请求，将车辆状态置位报修
        report(  that,bikeNo,types ,phoneNum, openId,latitude, longitude   );
        //TODO: 2.向日志系统记录log
      }
    });
  },
})

function report(  that,bikeNo,types,phoneNum,openId, latitude,longitude   ){
  wx.request({
    url: wx.getStorageSync('url')+"/repair",
    header: { 'content-type': 'application/x-www-form-urlencoded' },
    data: {
      phoneNum: phoneNum,
      bid: bikeNo,
      types: types,
      openId:openId,
      latitude:latitude,
      longitude:longitude
    },
    method: 'POST',
    success: function (res) {
      console.log( res );
      if( res.data.code==1){
        saveLog(that,bikeNo,latitude,longitude,openId,types);
        wx.showToast({
          title: '报修成功...谢谢',
        });
        wx.navigateTo({
            url: '../index/index',
      });
      }
    }
  })
}

function saveLog(that,bikeNo,latitude,longitude,openId,types){
  wx.request({
    url: wx.getStorageSync('url')+"/log/addRepairLog",
    data: {
      openId:openId,
      reportTime:new Date(),
      bid: bikeNo,
      types: types,
      latitude:latitude,
      longitude:longitude,
      repairPerson:"",
      repairTime:new Date()
    },
    method: 'POST',
  })
}
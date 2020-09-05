//index.js
//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    latitude: 0,
    longitude: 0,
    controls: [],
    markers: []
  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //console.log("生命周期：->onLoad")
    var that = this;
    //创建一个地图上下文，对地图进行事件操作
    that.mapCtx = wx.createMapContext('map');
    //获取当前手机所在的位置
    wx.getLocation({
      type: 'wgs84',
      isHighAccuracy: true,
      success: function (res) {
       // console.log(res);
        that.setData({
          latitude: res.latitude,
          longitude: res.longitude
        });
      }
    });

    wx.getSystemInfo({
      success(res) {
        //获取宽高
        var height = res.windowHeight;
        var width = res.windowWidth;
        //添加控件（图片）
        that.setData({
          controls: [{
              id: 1,
              position: {
                left: width / 2 - 10,
                top: height / 2 - 20,
                width: 20,
                height: 35
              },
              iconPath: "../images/location.png",
              clickable: true
            },
            {
              id: 2,
              position: {
                left: 20,
                top: height - 60,
                width: 40,
                height: 40
              },
              iconPath: "../images/img1.png",
              clickable: true
            },
            {
              id: 3,
              position: {
                left: 100,
                top: height - 60,
                width: 100,
                height: 40
              },
              iconPath: "../images/qrcode.png",
              clickable: true
            },
            {
              id: 4,
              position: {
                left: width - 45,
                top: height - 60,
                width: 40,
                height: 40
              },
              iconPath: "../images/pay.png",
              clickable: true
            }, { //报修
              id: 6,
              iconPath: "../images/repair.png",
              position: {
                width: 35,
                height: 35,
                left: width - 42,
                top: height - 203.
              },
              //是否可点击
              clickable: true
            }
          ]
        })
      }
    });
  },

  controltap(e) {
    var that = this;
    if (e.controlId == 2) {
      //地图复位
      that.mapCtx.moveToLocation();
    } else if (e.controlId == 3) {
      //获取全局变量status,根据它的值进行页面跳转
      var status = wx.getStorageSync('status');
      if (status == 0) {
        //跳到注册页面
        wx.navigateTo({
          url: '../register/register',
        });
      } else if (status == 1) {
        wx.navigateTo({
          url: '../deposit/deposit',
        });
      } else if (status == 2) {
        wx.navigateTo({
          url: '../identity/identity',
        });
      } else if (status == 3) {
        that.scanCode();
      } else if (status == 4) {
        wx.navigateTo({
          url: '../billing/billing',
        });
      }
    } else if (e.controlId == 4) {
      wx.navigateTo({
        url: '../pay/pay',
      });
    } else if (e.controlId == 6) {
      wx.navigateTo({
        url: '../repair/repair',
      });
    }
  },

  //重构扫码代码
  scanCode: function () {
    var that = this;
    //扫码
    wx.scanCode({
      success: function (res) {
        //console.log(  res );
        //得到 车的编号
        var bid = res.result;
        // that.data.latitude   that.data.longitude
        //异步请求
        wx.request({
          url: wx.getStorageSync('url')+"/open",
          method: "POST",
          //data: "bid="+bid+"&latitude="+that.data.latitude+"&longitude="+that.data.longitude,
          data: {
            bid: bid,
            latitude: that.data.latitude,
            longitude: that.data.longitude
          },
          dataType: "json",
          header: {
            "content-type": "application/json"
          },
          success: function (res) {
            if (res.data.code == 0) {
              wx.showToast({
                title: res.data.msg,
                icon:'none'
              })
            }

            //计费计时
            console.log(res);
            if (res.data.code == 1) {
              wx.setStorageSync('bid', bid);
              wx.setStorageSync('status', 4);
              getApp().globalData.status = 4;
              wx.navigateTo({
                url: '../billing/billing?bid=' + bid,
              });
            }
          }
        });
      }
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var that = this;
    //console.log("生命周期：->onReady");
    //数据埋点：用于获取用户openid，当前地理位置，发送请求到后台，并保存到mongo的logs表中，用于后期日志分析
    //获取位置
    wx.getLocation({
      success: function (res) {
        //获取经纬度
        var latitude = res.latitude;
        var longitude = res.longitude;
        //获取用户标识
        var openid = wx.getStorageSync('openid');
        wx.request({
          url: wx.getStorageSync('url')+'/log/savelog',
          data: {
            time: new Date(), //获取客户端时间
            openid: openid,
            latitude: latitude,
            longitude: longitude,
            url: 'index'
          },
          method: "POST"
        })
      }
    });
    findNearBikes(that, that.data.latitude, that.data.longitude);
  },

  regionchange: function (e) {
    var that = this;
    // e的事件type也两种值  begin，和 end
    if (e.type == 'end') {
      //要取出当前的位置，但请注意，这里不能用 wx.getLocation,因为它取的是设备的位置，这里要是移动后的地图位置. 
      that.mapCtx.getCenterLocation({
        success: function (res) {
          //这时的经纬度为地图新位置的中心点的经纬度
          findNearBikes(that, res.latitude, res.longitude);
        }
      })
    }
  },
})

function findNearBikes(that, latitude, longitude) {
  //获取附近的车并显示
  wx.request({
    url: wx.getStorageSync('url')+"/findNearAll",
    method: "POST",
    data: {
      latitude: latitude,
      longitude: longitude,
      status: 1
    },
    success: function (res) {
      const bikes = res.data.obj.map(item => {
        return {
          //bid: item.bid,
          iconPath: "/pages/images/bike.png",
          width: 35,
          height: 35,
          latitude: item.latitude,
          longitude: item.longitude
        }
      });
      that.setData({
        markers: bikes
      });
      // console.log(that.data.markers);
    }
  });
}
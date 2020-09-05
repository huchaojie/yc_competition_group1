// pages/register/register.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    countryCodes: ["86", "80", "84", "87"], //手机号码的国家编码
    countryCodeIndex: 0,
    phoneNum: "" //手机号
    //code: "" //验证码
  },
  bindCountryCodeChange: function (e) {
    //console.log('hello');
    this.setData({
      countryCodeIndex: e.detail.value
    })
  },

  inputPhoneNum: function (e) {
    this.setData({
      phoneNum: e.detail.value
    });
    //console.log(this.data.phoneNum);
  },

  inputCode: function (e) {
    this.setData({
      code: e.detail.value
    });
    //console.log(this.data.code);
  },

  formSubmit: function (e) {
    console.log("事件信息：" + e);
    var phoneNum = e.detail.value.phoneNum;
    var verifyCode = e.detail.value.verifyCode;
    var openid = wx.getStorageSync('openId');
    wx.request({
      url: wx.getStorageSync('url')+'/verify',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        phoneNum: phoneNum,
        verifyCode: verifyCode,
        status: 1,
        openId: openid,
        uuid: wx.getStorageSync('uuid')
      },
      method: "POST",
      success: function (res) {
        console.log("手机验证码校验结果" + res);
        if (res.data && res.data.code == 0) {
          wx.showModal({
            title: '提示',
            content: '注册失败，原因：' + res.data.msg + '!',
            showCancel: false
          })
          return
        }
        var globalData = getApp().globalData;
        wx.setStorageSync('phoneNum', phoneNum);
        globalData.status = 1;
        wx.setStorageSync('status', 1);
        wx.navigateTo({
          url: '../deposit/deposit',
        });
      }
    })
  },

  genVerifyCode: function (e) {
    //国家编码
    var index = this.data.countryCodeIndex;
    var countryCode = this.data.countryCodes[index];
    //电话号码
    var phoneNum = this.data.phoneNum;
    wx.request({
      url: wx.getStorageSync('url')+'/genCode',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        nationCode: countryCode,
        phoneNum: phoneNum
      },
      method: 'POST',
      success: function (e) {
        if (e.data.code == 1) {
          wx.showToast({
            title: '验证码已发送',
            icon: 'success'
          });
        }
      }
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})
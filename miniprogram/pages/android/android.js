// pages/android/android.js
const { miniAppPluginId } = require('../../constant');


Page({
  /**
   * 页面的初始数据
   */
  data: {
    plugin: undefined,
    quickStartContents: [
      '在「设置」->「安全设置」中手动开启多端插件服务端口',
      '在「工具栏」->「运行设备」中选择 Android 点击「运行」，快速准备运行环境',
      '在打开的 Android Stuido 中点击「播放」运行原生工程',
      '保持开发者工具开启，修改小程序代码和原生代码仅需在 Android Stuido 中点击「播放」查看效果',
    ]
  },

  onLoadPlugin() {
    wx.miniapp.loadNativePlugin({
      pluginId: miniAppPluginId,
      success: (plugin) => {
        console.log('加载 pda 扫码插件成功')
        this.setData({
          plugin
        })

        plugin.init({})

        plugin.pdaScan({}, this.handlePdaScan.bind(this))
      },
      fail: (e) => {
        console.log('load plugin fail', e)
      }
    })
  },

  handlePdaScan(data) {
    console.log('handlePdaScan', data)
    this.data.plugin.pdaScan({}, this.handlePdaScan.bind(this))
  },

  onReadText() {
    this.data.plugin.readText({
      text: "102",
    })
  },
  onUnloadPlugin() {
    this.data.plugin.unload({})
  }
})


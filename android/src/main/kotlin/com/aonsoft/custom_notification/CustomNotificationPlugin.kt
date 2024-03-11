package com.aonsoft.custom_notification

import androidx.annotation.NonNull

import android.content.Context
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugins.deviceinfo.DeviceInfoPlugin

/** CustomNotificationPlugin */
class CustomNotificationPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var context: Context
  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "custom_notification")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.MODEL}")
    } else if (call.method == "getDeviceInfo"){
      val deviceInfo = getDeviceInfo()
       result.success(deviceInfo)
    }else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  private fun getDeviceInfo(): Map<String, String> {
    val deviceInfoMap = HashMap<String, String>()
    val deviceInfoPlugin = DeviceInfoPlugin()
    val androidDeviceInfo = deviceInfoPlugin.androidInfo

    deviceInfoMap["os"] = "Android"
    deviceInfoMap["osVersion"] = android.os.Build.VERSION.RELEASE
    deviceInfoMap["device"] = android.os.Build.DEVICE
    deviceInfoMap["model"] = android.os.Build.MODEL

    return deviceInfoMap
  }
}

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'custom_notification_platform_interface.dart';

/// An implementation of [CustomNotificationPlatform] that uses method channels.
class MethodChannelCustomNotification extends CustomNotificationPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('custom_notification');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  Future<String?> getDeviceInfo() async {
    final version = await methodChannel.invokeMethod<String>('getDeviceInfo');
    return version;
  }
}

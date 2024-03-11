import 'package:flutter_test/flutter_test.dart';
import 'package:custom_notification/custom_notification.dart';
import 'package:custom_notification/custom_notification_platform_interface.dart';
import 'package:custom_notification/custom_notification_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockCustomNotificationPlatform
    with MockPlatformInterfaceMixin
    implements CustomNotificationPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final CustomNotificationPlatform initialPlatform = CustomNotificationPlatform.instance;

  test('$MethodChannelCustomNotification is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelCustomNotification>());
  });

  test('getPlatformVersion', () async {
    CustomNotification customNotificationPlugin = CustomNotification();
    MockCustomNotificationPlatform fakePlatform = MockCustomNotificationPlatform();
    CustomNotificationPlatform.instance = fakePlatform;

    expect(await customNotificationPlugin.getPlatformVersion(), '42');
  });
}

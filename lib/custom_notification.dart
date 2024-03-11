
import 'custom_notification_platform_interface.dart';

class CustomNotification {
  Future<String?> getPlatformVersion() {
    return CustomNotificationPlatform.instance.getPlatformVersion();
  }
}

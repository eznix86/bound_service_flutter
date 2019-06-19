import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:bound_service/bound_service.dart';

void main() {
  const MethodChannel channel = MethodChannel('bound_service');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await BoundService.platformVersion, '42');
  });
}

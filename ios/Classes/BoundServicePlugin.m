#import "BoundServicePlugin.h"
#import <bound_service/bound_service-Swift.h>

@implementation BoundServicePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftBoundServicePlugin registerWithRegistrar:registrar];
}
@end

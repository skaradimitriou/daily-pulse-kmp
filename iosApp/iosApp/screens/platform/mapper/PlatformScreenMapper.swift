import SharedLogic

extension InfoType {
    
    var localizedLabel: String {
        switch self {
        case .osName:
            return String(localized: "platform_os_name_item_label")
        case .osVersion:
            return String(localized: "platform_os_version_item_label")
        case .deviceModel:
            return String(localized: "platform_device_item_label")
        case .screenDensity:
            return String(localized: "platform_density_item_label")
        }
    }
}

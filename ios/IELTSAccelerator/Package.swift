// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "IELTSAccelerator",
    platforms: [
        .iOS(.v16),
        .macOS(.v13)
    ],
    products: [
        .library(
            name: "IELTSAccelerator",
            targets: ["IELTSAccelerator"]
        ),
    ],
    targets: [
        .target(
            name: "IELTSAccelerator",
            path: "."
        ),
    ]
)

# Base Project
MVVM 快速开发工程

## 模块说明
* app
主程序
* lib
通用组件，例如 ProgressDialog 之类通用的 View，也可以有单位转换之类的工具类
* network
网络模块，retrofit 的封装，以及所有的 response

## MVVM 部分说明
### 目录结构
lib/
- MVVMBaseActivity
- MVVMBaseFragment
- MVVMBaseViewModel
- MVVMLifecycle
- MVVMViewModelFactory

### 要点
主要有两点，在 MVVMViewModelFactory 中
1. 将 Activity 的生命周期传给 ViewModel，使其可以直接获取 onCreate()、onDestroy() 等状态，免去手动调用
2. 将 Activity - intent - bundle 和 Fragment - arguments 在绑定 ViewModel 时传入，在 viewModel 执行 onCreate() 生命周期时就可以读取到传入的数据

## TDOD
完善 README
DataBinding Adapter
完善网络模块，更新逻辑，增加 log 显示等
其它







# modules
各个独立module集合
    - photoview：photoview的库
icon：常用svg的icon搜集库
adapter：listview和RecyclerView的适配器和viewHolder的抽象封装
kit：常用工具类的
refresh：RefreshView和XRecyclerView的封装，包含26中指示器
oklib：OKHttp的封装库，处理网络请求

# 接入指南
### 第一步：Project的build.gradle文件统一配置module的配置信息
ext {
    buildToolsVersion = "30.0.2"
    compileSdkVersion = 30
    minSdkVersion = 21
    targetSdkVersion = 26
    versionCode = 1
    versionName = "1.0.0"
}

### 第二步：settings.gradle导入module 并指导路径,指定路径时：'..'标识上级目录
例如：'../modules'表示当前项目根目录的上级目录，即和当前项目根文件夹同级目录下的modules目录 
例如：'../../modules'表示当前项目根目录的上级目录，即和当前项目根文件夹同级目录下的modules目录

include ':icon'
project(':icon').projectDir =
        new File(settingsDir, '../modules/icon')

include ':photoview'
project(':photoview').projectDir =
        new File('../modules/photoview')

include ':adapter'
project(':adapter').projectDir =
        new File('../modules/adapter')

include ':kit'
project(':kit').projectDir =
        new File('../modules/kit')

include ':refresh'
project(':refresh').projectDir =
        new File('../modules/refresh')

include ':oklib'
project(':oklib').projectDir =
        new File('../modules/oklib')

### 第三步：依赖modules
implementation project(path: ':oklib')
implementation project(path: ':refresh')
implementation project(path: ':kit')
implementation project(path: ':adapter')
implementation project(path: ':icon')

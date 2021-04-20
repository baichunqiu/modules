# modules
各个独立module集合
## 接入步骤
### 第一步：Project的build.gradle文件统一配置module的配置信息
ext {
    buildToolsVersion = "30.0.2"
    compileSdkVersion = 30
    minSdkVersion = 21
    targetSdkVersion = 26
    versionCode = 1
    versionName = "1.0.0"
}

### 第二步：settings.gradle导入module 并指导路径,指定路径时：'..'标识上级目录，例如：'../modules'表示当前项目根目录的上级目录，即和当前项目根文件夹同级目录下的modules目录 例如：'../../modules'表示当前项目根目录的上级目录，即和当前项目根文件夹同级目录下的modules目录
include ':photoview', ':oklib', ':refresh', ':kit', ':adapter', ':icon'//single module
project(':icon').projectDir = new File(settingsDir, '../modules/icon')
project(':photoview').projectDir = new File('../modules/photoview')
project(':adapter').projectDir = new File('../modules/adapter')
project(':kit').projectDir = new File('../modules/kit')
project(':refresh').projectDir = new File('../modules/refresh')
project(':oklib').projectDir = new File('../modules/oklib')

### 第三步：依赖modules
implementation project(path: ':oklib')
implementation project(path: ':refresh')
implementation project(path: ':kit')
implementation project(path: ':adapter')
implementation project(path: ':icon')
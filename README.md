# register-for-result
register for result is very powerful library in android
google has been switched startActivityForResult() to registerForActivityResult() 
so you can use this library to (get data from another activity , camira , mic , ATC....)
   
## Setup Guide
```allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
   }
}
 ```
 ```
 dependencies {
	        implementation 'com.github.KareemMansy123:TestLog:1.0.1'
	}
  ```
  ## Usage Kotlin
```
ActivityResultRequester(context as FragmentActivity).request( Your Intent here ){ 
//todo your response here 
}
```
  

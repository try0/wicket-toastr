# wicket-toastr


[![Build Status](https://travis-ci.org/try0/wicket-toastr.svg?branch=develop)](https://travis-ci.org/try0/wicket-toastr) 
[![codecov](https://codecov.io/gh/try0/wicket-toastr/branch/develop/graph/badge.svg)](https://codecov.io/gh/try0/wicket-toastr) 
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=try0_wicket-toastr&metric=alert_status)](https://sonarcloud.io/dashboard?id=try0_wicket-toastr) 
[![Maven Central](https://img.shields.io/maven-central/v/jp.try0.wicket/wicket-toastr-core.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22jp.try0.wicket%22%20AND%20a:%22wicket-toastr-core%22) 
[![Javadocs](https://www.javadoc.io/badge/jp.try0.wicket/wicket-toastr-core.svg?color=lightgrey)](https://www.javadoc.io/doc/jp.try0.wicket/wicket-toastr-core)


[Apache Wicket](https://wicket.apache.org/) utilities for using [toastr](https://codeseven.github.io/toastr/) which is library of a simple javascript toast notification.  
Just add behavior to component, you can display toast.
This behavior use wicket feedback message system.  
Also, you can display toast manually without use feedback message.


## Version
|  |toastr  |wicket  |
|--:|--:|--:|
|1.0.2  |2.1.4  |8.x  |


## Demo
Deployed wicket-toastr-samples module.  
[demo](https://try0.jp/app/wicket-toastr-samples/)


## Usage

### Maven
```xml
<dependency>
    <groupId>jp.try0.wicket</groupId>
    <artifactId>wicket-toastr-core</artifactId>
    <version>1.0.2</version>
</dependency>
```

### Initialize Settings
You can set default values, in the application initialize process(Application#init).
```java
ToastrSettings.createInitializer(this)
.setAutoAppendBehavior(true)
.setMessageFilter(filter)
.setToastrBehaviorFactory(factory)
.initialize();
```


### Display toast with ToastrBehavior

add ToastrBehavior to any of components in page
```java
add(new ToastrBehavior());
```
or set true to setAutoAppendBehavior on initialize settings
```java
ToastrSettings.createInitializer(this)
.setAutoAppendBehavior(true)
```

Component#success(Serializable), Session#success(Serializable)  
⇒ success toast  
<img alt="toast_success" src="https://user-images.githubusercontent.com/17096601/45070139-dd932f00-b10a-11e8-8c8a-b539945fcdc1.png" width="350px">

Component#info(Serializable), Session#info(Serializable)  
⇒ info toast  
<img alt="toast_info" src="https://user-images.githubusercontent.com/17096601/45070137-dd932f00-b10a-11e8-85ec-03036d325299.png" width="350px">

Component#warn(Serializable), Session#warn(Serializable)  
⇒ warn toast  
<img alt="toast_warn" src="https://user-images.githubusercontent.com/17096601/45070136-dd932f00-b10a-11e8-85b8-69ff6accf8e1.png" width="350px">

Component#error(Serializable), Session#error(SerializableSerializable)  
Component#fatal(Serializable), Session#fatal(Serializable)  
⇒ error toast  
<img alt="toast_error" src="https://user-images.githubusercontent.com/17096601/45070135-dcfa9880-b10a-11e8-8b32-9f1741bb2925.png" width="350px">

### Display toast manually
In this case, need instance of class that implemented IHeaderResponse or AjaxRequestTarget.

```java
Toast.create(toastLevel, message)
.show(target);
```

### Others
#### With title
```java
Toast.create(toastLevel, message)
.withTitle(title)
.show(target);
```

#### With options
Overrides global options.
```java
Toast.create(toastLevel, message)
.withToastOptions(options)
.show(target);
```


## Author
Ryo Tsunoda  
[GitHub](https://github.com/try0)  
[Twitter](https://twitter.com/0yrt_)


## Licence
```
   Copyright 2018 Ryo Tsunoda

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

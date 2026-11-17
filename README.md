# wicket-toastr


[![Build Status](https://github.com/try0/wicket-toastr/workflows/Java%20CI/badge.svg)](https://github.com/try0/wicket-toastr/actions?query=workflow%3A%22Java+CI%22) 
[![codecov](https://codecov.io/gh/try0/wicket-toastr/branch/develop/graph/badge.svg)](https://codecov.io/gh/try0/wicket-toastr) 
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=try0_wicket-toastr&metric=alert_status)](https://sonarcloud.io/dashboard?id=try0_wicket-toastr) 
[![Maven Central](https://img.shields.io/maven-central/v/jp.try0.wicket/wicket-toastr-core.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/jp.try0.wicket/wicket-toastr-core) 
[![Javadocs](https://www.javadoc.io/badge/jp.try0.wicket/wicket-toastr-core.svg?color=lightgrey)](https://www.javadoc.io/doc/jp.try0.wicket/wicket-toastr-core)


[Apache Wicket](https://wicket.apache.org/) utilities for [toastr](https://codeseven.github.io/toastr/), a simple JavaScript library for toast notifications.  
Add a behavior to a component, and Wicket feedback messages are displayed as toasts.  
You can also display toasts manually without using feedback messages.


## Version
| Version | Wicket | Java | toastr |
| ---- | ---- | ---- | ---- |
| 4.0.0 | 11.x | 21 | 2.1.4 |
| 3.0.0 | 10.x | 17 | 2.1.4 |


## Demo
A live demo of the [wicket-toastr-samples](https://try0.jp/app/wicket-toastr-samples/) module is available.


## Usage

### Maven

Add the wicket-toastr-core dependency.

Wicket 11.x
```xml
<dependency>
    <groupId>jp.try0.wicket</groupId>
    <artifactId>wicket-toastr-core</artifactId>
    <version>4.0.0</version>
</dependency>
```

Wicket 10.x
```xml
<dependency>
    <groupId>jp.try0.wicket</groupId>
    <artifactId>wicket-toastr-core</artifactId>
    <version>3.0.0</version>
</dependency>
```

### jQuery
toastr requires jQuery.  
wicket-toastr automatically renders the jQuery configured in `JavaScriptLibrarySettings#getJQueryReference()` (jQuery 4.0.0 by default in Wicket 11) before toastr.js.  
jQuery is still loaded even if you switch Wicket Ajax to its jQuery-free implementation (`setWicketAjaxReference(WicketAjaxResourceReference.get())`).

### Initialize Settings
You can configure defaults during application initialization (`Application#init`).
```java
ToastrSetting.createInitializer(this)
.setAutoAppendBehavior(true)
.setMessageFilter(filter)
.setToastrBehaviorFactory(factory)
.initialize();
```


### Display toasts with ToastrBehavior

Add `ToastrBehavior` to any component on the page.
```java
add(new ToastrBehavior());
```
Alternatively, call `setAutoAppendBehavior(true)` during initialization to add it to every page automatically.
```java
ToastrSetting.createInitializer(this)
.setAutoAppendBehavior(true)
.initialize();
```

`ToastrBehavior` displays feedback messages as toasts.

`Component#success(Serializable)`, `Session#success(Serializable)`  
⇒ success toast  
<img alt="toast_success" src="https://user-images.githubusercontent.com/17096601/45070139-dd932f00-b10a-11e8-8c8a-b539945fcdc1.png" width="350px">

`Component#info(Serializable)`, `Session#info(Serializable)`  
⇒ info toast  
<img alt="toast_info" src="https://user-images.githubusercontent.com/17096601/45070137-dd932f00-b10a-11e8-85ec-03036d325299.png" width="350px">

`Component#warn(Serializable)`, `Session#warn(Serializable)`  
⇒ warning toast  
<img alt="toast_warn" src="https://user-images.githubusercontent.com/17096601/45070136-dd932f00-b10a-11e8-85b8-69ff6accf8e1.png" width="350px">

`Component#error(Serializable)`, `Session#error(Serializable)`  
`Component#fatal(Serializable)`, `Session#fatal(Serializable)`  
⇒ error toast  
<img alt="toast_error" src="https://user-images.githubusercontent.com/17096601/45070135-dcfa9880-b10a-11e8-8b32-9f1741bb2925.png" width="350px">

### Display toasts manually
To display a toast without feedback messages, pass an `AjaxRequestTarget` or an `IHeaderResponse` to `show`.

```java
Toast.create(toastLevel, message)
.show(target);
```

### Options
#### Title
```java
Toast.create(toastLevel, message)
.withTitle(title)
.show(target);
```

#### Toast options
Options set on a toast override the global options.
```java
Toast.create(toastLevel, message)
.withToastOption(option)
.show(target);
```

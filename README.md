# wicket-toastr

[Apache Wicket](https://wicket.apache.org/) utilities for using [toastr](https://codeseven.github.io/toastr/) which is library of a simple javascript toast notification.

Just add behavior to component, you can display toast.
This behavior use wicket feedback message system.  
Also, you can display toast manually without use feedback message.


## Demo
Deployed wicket-toastr-samples module.  
[demo](https://try0.jp/app/wicket-toastr-samples/)

## Version
|  |toastr  |wicket  |
|--:|--:|--:|
|1.0.0  |2.1.4  |8.x  |


## Maven
```
TODO
```

## Initialize Settings
You can set default values.
Please call this method or one of several overload methods, in the application initialize process(Application#init).
```java
// arg1: Application
// arg2: Whether to always add toastr behavior to new pages
// arg3: Toastr default options
ToastrSettings.initialize(this, true, options);
```

## Display toast with ToastrBehavior

add ToastrBehavior to any of components in page
```java
add(new ToastrBehavior());
```
or set "true" to second argument on initialize settings

```java
// arg1: Application
// ★arg2: Whether to always add toastr behavior to new pages
// arg3: Toastr default options
ToastrSettings.initialize(this, true, options);
```

Component#success, Session#success  
⇒ displayed success toast  
<img alt="toast_success" src="https://user-images.githubusercontent.com/17096601/45070139-dd932f00-b10a-11e8-8c8a-b539945fcdc1.png" width="350px">

Component#info, Session#info  
⇒ displayed info toast  
<img alt="toast_info" src="https://user-images.githubusercontent.com/17096601/45070137-dd932f00-b10a-11e8-85ec-03036d325299.png" width="350px">

Component#warn, Session#warn  
⇒ displayed warn toast  
<img alt="toast_warn" src="https://user-images.githubusercontent.com/17096601/45070136-dd932f00-b10a-11e8-85b8-69ff6accf8e1.png" width="350px">

Component#error, Session#error  
Component#fatal, Session#fatal  
⇒ displayed error toast  
<img alt="toast_error" src="https://user-images.githubusercontent.com/17096601/45070135-dcfa9880-b10a-11e8-8b32-9f1741bb2925.png" width="350px">

## Display toast manually

In this case, need instance of class that implemented IHeaderResponse or AjaxRequestTarget.

```java
Toast.create(toastLevel, message)
.show(target);
```

## Others
### With title
```java
Toast.create(toastLevel, message)
.withTitle(title)
.show(target);
```

### With options
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
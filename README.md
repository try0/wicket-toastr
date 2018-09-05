# wicket-toastr

Apach Wicket utilities for [toastr](https://github.com/CodeSeven/toastr).

Just add behaivior to component, you can display toast.
This behavior use wicket feedback message.

Also, you can display toast manually without use feedback message.  



## Version
use toastr 2.1.4, wicket 8.0.0

## Initialize Settings
You can set default values.
Call this code in the Application#init.
```java
// arg1: Application
// arg2: Whether to always add toastr behavior to new pages
// arg3: Toastr default options
ToastrSettings.setUp(this, true, options);
```

## Display toast with ToastrBehavior

add ToastrBehavior to any of components in page
```java
add(new ToastrBehavior());
```
or set second argument "true" on initialize settings

```java
// arg1: Application
// ★arg2: Whether to always add toastr behavior to new pages
// arg3: Toastr default options
ToastrSettings.setUp(this, true, options);
```

Component#success, Session#success ⇒ displayed success toast  
<img alt="toast_success" src="https://user-images.githubusercontent.com/17096601/45070139-dd932f00-b10a-11e8-8c8a-b539945fcdc1.png" width="350px">
  
Component#info, Session#info ⇒ displayed info toast  
<img alt="toast_info" src="https://user-images.githubusercontent.com/17096601/45070137-dd932f00-b10a-11e8-85ec-03036d325299.png" width="350px">

Component#warn, Session#warn ⇒ displayed warn toast  
<img alt="toast_warn" src="https://user-images.githubusercontent.com/17096601/45070136-dd932f00-b10a-11e8-85b8-69ff6accf8e1.png" width="350px">
  
Component#error, Session#error ⇒ displayed error toast  
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
.setToastTitle(title)
.show(target);
```

### Overrides global options
```java
Toast.create(toastLevel, message)
.setToastOptions(options)
.show(target);
```


## License
TODO


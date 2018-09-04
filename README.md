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
Component#info, Session#info ⇒ displayed info toast  
Component#warn, Session#warn ⇒ displayed warn toast  
Component#error, Session#error ⇒ displayed error toast  

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


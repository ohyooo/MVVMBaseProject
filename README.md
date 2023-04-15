# Android MVVM Base Project

------

## Introduction

This project is intent to provide a template with basic MVVM architecture framework. You can just copy it to workspace instead of creating a new project from Android Studio.

Advantages:

- Simple, easy to read
- Use few libs, save time for gradle syncing
- No dagger or any other DI lib

## Module

```cmd
├───app
│   ├───app       Application
│   ├───model     models
│   ├───ui        activities & fragments
│   │   ├───main  MainActivity
│   └───viewmodel viewmodels
│
├───lib
│   ├───adapter   databinding adapter
│   ├───extension kotlin extensions
│   └───mvvm      MVVM framework
│
└───network
    ├───api
    ├───model
    └───repository
```

## MVVM
Usually, a viwemodel can only aware the destroy of its owner in onClear() method. But after making it implements LifecycleObserver and observing owner's lifecycle in ViewModelProvider.Factory. It can use onCreate() or other lifecycle event now.
Check these codes in MVVMViewModelFactory.kt

## Todo
many many things..
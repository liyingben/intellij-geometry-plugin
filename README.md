# intellij-geometry-plugin
show wkt in jts. During debugging, the geometry object of the monitoring variable WKT can be drawn. 
WKT format
```commandline
POINT(1.234 5.678)     
LINESTRING (0 0, 10 10, 20 20)
POLYGON ((1 1, 1 2, 4 1.25, 4 1, 1 1))
```





[![JetBrains Plugins](https://plugins.jetbrains.com/files/21630/327074/icon/pluginIcon.svg)](https://plugins.jetbrains.com/plugin/21630-geometry)


Installation
------------
### Plugin Installation
- Using IDE built-in plugin system on Windows:
    - <kbd>File</kbd> > <kbd>Settings</kbd> > <kbd>Plugins</kbd> > <kbd>Browse repositories...</kbd> > <kbd>Search for "geometry"</kbd> > <kbd>Install Plugin</kbd>
- Using IDE built-in plugin system on MacOs:
    - <kbd>Preferences</kbd> > <kbd>Settings</kbd> > <kbd>Plugins</kbd> > <kbd>Browse repositories...</kbd> > <kbd>Search for "geometry"</kbd> > <kbd>Install Plugin</kbd>
- Manually:
    - Download the [latest release](https://github.com/liyingben/intellij-geometry-plugin/releases/latest) and install it manually using <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Install plugin from disk...</kbd>

Restart IDE.

### Required IntelliJ Configuration
In your project: Click <kbd>Preferences</kbd> -> <kbd>Build, Execution, Deployment</kbd> -> <kbd>Compiler, Annotation Processors</kbd>. Click <kbd>Enable Annotation Processing</kbd>

Afterwards you might need to do a complete rebuild of your project via <kbd>Build</kbd> -> <kbd>Rebuild Project</kbd>.




License
-------
Copyright (c) 2011-2020 Michail Plushnikov. See the [LICENSE](./LICENSE) file for license rights and limitations (BSD).

[github]:           https://github.com/liyingben/intellij-geometry-plugin


# RubyTypes

## Control flow printer

Plugin that provides control flow graph data for active file.

### Run:

`./gradlew controlflow:runIde`

**OR**

Inside Intellij IDEA, open Gradle tool window: 
`View - Tool windows - Gradle`

In Gradle tool window, find `runIde` task: `RubyTypes - :controlflow - Tasks - intellij - runIde`

Then double-click on task or select `Run 'RubyTypes:controlflow:runIde'` in options.
### In launched IJ instance

Make sure that some Ruby file is active in editor.

Then go to `Analyze` menu and select `Print control flow graph for active file` menu item.

Control flow information must appear in pop-up window.
# Laminar Web Components

This repository provides [Laminar](https://github.com/raquo/Laminar) type definitions for web component collections. 
Currently included collections consist of:

- [Google's Material Web Components](https://github.com/material-components/material-components-web-components)
- [Ossum Web Components](https://github.com/laminar-web-components/ossum-web-components)

## Usage

### Add dependency

This package is published to GitHub Packages in this repository. To reference it, 
you can use the [sbt-github-packages](https://github.com/djspiewak/sbt-github-packages) SBT plugin:

```
resolvers += Resolver.githubPackages("ossuminc")

libraryDependencies ++= Seq(
  "com.ossuminc" %%% "laminar-web-components-material" % "0.1.0",
  "com.ossuminc" %%% "laminar-web-components-ossum" % "0.1.0"
)
```

### Ensure you have [sbt-scalajs-bundler](https://scalacenter.github.io/scalajs-bundler/) plugin

Because web components have NPM dependencies, we need to use `sbt-scalajs-bundler` to 
help us resolve those NPM dependencies. Place this in your `project/plugins.sbt` file:

```
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.14.0")
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.17.0")
```

### Use components in your app

Web Components have a similar interface as the built-in elements. 
See the examples below for some inspiration.

## Examples

### Component Showcase

Displays a collection of Web Components

To run:
  - `cd examples/showcase`
  - `sbt fastOptJS::webpack`
  - Open `index-fastopt.html` in browser

## Generator

[generator](https://github.com/uosis/laminar-web-components/tree/master/generator) folder 
contains Ammonite scripts used to generate component type definitions.
If you want to tweak the generated types, changes should be made here.

To run code generation:

- `amm generator/main.sc`
- Format `material/src/main/scala/material.scala` using scalafmt
- Check in the new generated code


### Adding new component to existing collection or modifying existing component

- Add/modify the component in the generation script
  - For example, if it's a new Google Material Web Component, add it to `material.sc` 
    using one of the existing components as an example.
- Run code generation as described above
- Check in generated code


### Adding new component collection

- Create new definitions script using `material.sc` as an example
- Create new SBT project using `material/` project as an example
- Update `main.sc` to include new project
- Run code generation
- Check in generated code

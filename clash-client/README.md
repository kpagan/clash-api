# ClashClient

## Setup the local development environment

Due to the fact that the project was built with Angular 10 several versions of the tools need to be locked because it won't run with the latest versions.

Install a 10.x.x release of Node.js from https://nodejs.org/dist/latest-v10.x/.

Install the Angular CLI

```
npm install -g @angular/cli@10
```

Install node-sass version `4.11.0` if necessary
```
npm install node-sass@4.11.0
```

This project was initially generated with [Angular CLI](https://github.com/angular/angular-cli) version 7.1.3.
Updated gradualy to Angular `8`, `9` and finally `10` with the commands 
```
ng update @angular/core@<VERSION> @angular/cli@<VERSION>
ng update @angular/material@<VERSION>
```
where `<VERSION>` is the major version e.g. 8, 9, 10

After update Angular CLI is version `10.2.0`

Node.js version `10.23.0`

## Launching the UI

Run `npm start` and the UI will be compiled and launched in the default browser.

## Creating the Markdown

This markdown was edited using online service http://euangoddard.github.io/clipboard2markdown/

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4400/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

# Angular Material
To generate sample material components check https://material.angular.io/guide/schematics

Angular Material comes packaged with Angular CLI schematics to make creating Material applications easier.

### [](https://material.angular.io/guide/schematics#install-schematics "Link to this heading") Install Schematics

Schematics are included with both `@angular/cdk` and `@angular/material`. Once you install the npm packages, they will be available through the Angular CLI.

Using the command below will install Angular Material, the [Component Dev Kit](https://material.angular.io/cdk) (CDK), and [Angular Animations](https://angular.io/guide/animations) in your project. Then it will run the install schematic.

```
ng add @angular/material
```

In case you just want to install the `@angular/cdk`, there are also schematics for the [Component Dev Kit](https://material.angular.io/cdk)

```
ng add @angular/cdk
```

The Angular Material `ng add` schematic helps you setup an Angular CLI project that uses Material. Running `ng add` will:

-   Ensure [project dependencies](https://material.angular.io/guide/getting-started#step-1-install-angular-material-angular-cdk-and-angular-animations) are placed in `package.json`
-   Enable the [BrowserAnimationsModule](https://material.angular.io/guide/getting-started#step-2-configure-animations) your app module
-   Add either a [prebuilt theme](https://material.angular.io/guide/theming#using-a-pre-built-theme) or a [custom theme](https://material.angular.io/guide/theming#defining-a-custom-theme)
-   Add Roboto fonts to your `index.html`
-   Add the [Material Icon font](https://material.angular.io/guide/getting-started#step-6-optional-add-material-icons) to your `index.html`
-   Add global styles to
    -   Remove margins from `body`
    -   Set `height: 100%` on `html` and `body`
    -   Make Roboto the default font of your app
-   Install and import `hammerjs` for [gesture support](https://material.angular.io/guide/getting-started#step-5-gesture-support) in your project

Component schematics
--------------------

In addition to the install schematic, Angular Material comes with multiple schematics that can be used to easily generate Material Design components:

| Name | Description |
| --- | --- |
| `address-form` | Component with a form group that uses Material Design form controls to prompt for a shipping address |
| `navigation` | Creates a component with a responsive Material Design sidenav and a toolbar for showing the app name |
| `dashboard` | Component with multiple Material Design cards and menus which are aligned in a grid layout |
| `table` | Generates a component with a Material Design data table that supports sorting and pagination |
| `tree` | Component that interactively visualizes a nested folder structure by using the `<mat-tree>`component |

Additionally the Angular CDK also comes with a collection of component schematics:

| Name | Description |
| --- | --- |
| `drag-drop` | Component that uses the `@angular/cdk/drag-drop` directives for creating an interactive to-do list |

#### [](https://material.angular.io/guide/schematics#address-form-schematic "Link to this heading") Address form schematic

Running the `address-form` schematic generates a new Angular component that can be used to get started with a Material Design form group consisting of:

-   Material Design form fields
-   Material Design radio controls
-   Material Design buttons

```
ng generate @angular/material:address-form <component-name>
```

#### [](https://material.angular.io/guide/schematics#navigation-schematic "Link to this heading") Navigation schematic

The `navigation` schematic will create a new component that includes a toolbar with the app name and a responsive side nav based on Material breakpoints.

```
ng generate @angular/material:nav <component-name>
```

#### [](https://material.angular.io/guide/schematics#table-schematic "Link to this heading") Table schematic

The table schematic will create a component that renders an Angular Material `<table>` which has been pre-configured with a datasource for sorting and pagination.

```
ng generate @angular/material:table <component-name>
```

#### [](https://material.angular.io/guide/schematics#dashboard-schematic "Link to this heading") Dashboard schematic

The `dashboard` schematic will create a new component that contains a dynamic grid list of Material Design cards.

```
ng generate @angular/material:dashboard <component-name>
```

#### [](https://material.angular.io/guide/schematics#tree-schematic "Link to this heading") Tree schematic

The `tree` schematic can be used to quickly generate an Angular component that uses the Angular Material `<mat-tree>` component to visualize a nested folder structure.

```
ng generate @angular/material:tree <component-name>
```

#### [](https://material.angular.io/guide/schematics#drag-and-drop-schematic "Link to this heading") Drag and Drop schematic

The `drag-drop` schematic is provided by the `@angular/cdk` and can be used to generate a component that uses the CDK drag and drop directives.

```
ng generate @angular/cdk:drag-drop <component-name>
```
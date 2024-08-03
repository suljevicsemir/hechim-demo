# Hechim Demo - Fitness pal for everyday use

## Short insight what this project is about to casual or more interested reader.
- What this project is about
  This project serves a purpose to demonstrate my skills in Android Development (Jetpack Compose) to potential clients. As a product, this project isn't necessarily usable as a fitness app - but rather as an insight into my skils.
- Setup, arhictecture, testing
  I'll walk you through this project's setup (how to run it if you want). Project's architecture (feature based architecture) and in the end unit testing. Only some features of the are covered by unit tests - as this app isn't in any kind of production, we write unit tests for demonstration.
- App is built to support two different ways of *operating*. It's either using Firebase as backend (database, auth...) or API. This is just to showcase knowledge of DI in Android but can actually come in handy in cases where we want to start with POC and quickly assemble working app using Firebase then switch to API (without impacting UI, View Model, Domain Layer - if present, data and interfaces remain the same). Pretty much for each repository we would have two different implementation classes (AuthenticationFirebase and AuthenticationApi) and they are configured at build time, let's say using a boolean flag from build config.
- App is built using multi-module architecture (feature based).
  - UI module, contains reusable components used throughout the app (buttons, text buttons, list items). Should not be aware of any feautere specifc and is only aware of Foundation module. Responsible for navigation as well.
  - Foundation module, contains features that build the app such as auth, legal, privacy and so on - along it's implementation classes and it's dependency injection. Also responsible for local storage (datastore, database, preferences).

  

# Hechim Demo - Fitness pal for everyday use

This repo contains code for a fitness app - BUT serves only as a CV projected to demonstrate skills in Android with Jetpack Compose and it's not meant to be production ready app. It's possible that certain features won't even make sense but they are included as a way to demonstrate skills. It's a 'fitness app' just to include permission handling as much as possible.
Code mainly focuses:
- clean MVVM
- Hilt dependency injection (certain features to support different implementation classes at runtime. Sometimes Firebase, sometimes API).
- multi-module architecture
- unit testing
- handling different UI states with grace

Certain parts of the skills demonstration will also be:
- navigation
- UI grace handling (loading, error, success, content)

  

## Short insight what this project is about to casual or more interested reader.
- What this project is about
  This project serves a purpose to demonstrate my skills in Android Development (Jetpack Compose) to potential clients. As a product, this project isn't necessarily usable as a fitness app - but rather as an insight into my skils.
- Setup, arhictecture, testing
  I'll walk you through this project's setup (how to run it if you want). Project's architecture (feature based architecture) and in the end unit testing. Only some features of the are covered by unit tests - as this app isn't in any kind of production, we write unit tests for demonstration.
- App is built to support two different ways of *operating*. It's either using Firebase as backend (database, auth...) or API. This is just to showcase knowledge of DI in Android but can actually come in handy in cases where we want to start with POC and quickly assemble working app using Firebase then switch to API (without impacting UI, View Model, Domain Layer - if present, data and interfaces remain the same). Pretty much for each repository we would have two different implementation classes (AuthenticationFirebase and AuthenticationApi) and they are configured at build time, let's say using a boolean flag from build config.
- App is built using multi-module architecture (feature based).
  - UI module, contains reusable components used throughout the app (buttons, text buttons, list items). Should not be aware of any feautere specifc and is only aware of Foundation module. Responsible for navigation as well.
  - Foundation module, contains features that build the app such as auth, legal, privacy and so on - along it's implementation classes and it's dependency injection. Also responsible for local storage (datastore, database, preferences).
  - Onboarding module, contains all code related to pre-auth and auth flow. This includes Onboarding tutorials, language selection, login, register, name screen and most importantly permissions and trapdoor lockout.
  - Dashboard module, contains certain post auth features (settings, homepage..).
  - app module, initializes the app, start routes and is aware of all other modules.
 
## Module architecture
Each module has at the top level two package names *api* and *internal*. Api has public interface and contains code that can be accessed by users of the module. Internal contains behind the sceens implementation that can't be altered. Every module should be separated into sub-features (if possible) like onboarding is divided into: permissions, trapdoor, onboarding. Api package is later divided into.
- config: contains any feature specific configurations such as constants, data classes and so on.
- navigation (if exists): if feature has it's own internal navigation (such as permissions, dashboard with bottom navigation bar) it's setup in this package name
- ui: all composables (or views) should go in here
- viewmodel: if feature has multiple view models, they all go in here. For instance, if screen has multiple complex view, each view can have it's own view model
 
### Onboarding
- Short explanation of onboarding module (feature). What user goes through, why do we request permissions and implement trapdoor lockout.
- Onboarding module is then separated into three features:
  - permissions
  - trapdoor
  - onboarding
- Onboarding contains a short explanation about the app (mostly AI generated text), language selection screen.
- Auth flow contains login, register, name input screen.
- The most important part (for app and skills demo) are the permissions flow and trapdoor lockout.
- Permissions are listed after every login until at least once user is able to navigate to homepage (irrelevant if they granted permissions, important that they've been prompted the last permission).
  - Permissions can be asked up to two times (two rejections)
  - If user keeps force closing the app, they will be forced to permissions flow every time.
  - When user finally 'completes' the permissions flow they are redirected to homepage.
- Trapdoor lockout interferes with users, not granting them app usage until they grant all necessary permissions. Trapdoor lockout is displayed over the content of the entire app which means if user from any screen in the app:
  - backgrounds the app
  - revokes some permissions (app is likely to be killed by the system)
  - goes back to the app
  - trapdoor will be displayed
Same goes for giving the permissions while trapdoor is present.
  - background the app
  - allows some permissions (apps are rarely killed when permissions are given from the app settings)
  - goes back to the app
  - trapdoor isn't present
 
### Dashboard
- Dashboard contains all the post-auth content in the app. Depending on time of reading, it features settings section (privacy policy, terms, about us, change password, user profile with update).
- Settings section in Dashboard will be used to demonstrate skills with dependency injection (different usages of Firebase vs API).



  

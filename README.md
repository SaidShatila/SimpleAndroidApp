# SimpleAndroidApp
##A simple Android Application representing the most common libraries in Jetpack 

## This sample was created to showcase my skills and the latest learnings in the Android Framework.
### Android Frameworks Used:
#### Used MVVM.
#### Used ViewModels, Hilt, Preference DataStore.
#### Used DataStore .
#### Kotlin-coroutines were used in order to fetch the saved Data.
#### Retrofit for network calls.
#### Progguard for code obfuscation.

## Architecture:
I am using the MVVM architecture and some state machine concept on top of it. Every screen has a view, a model, and a ViewModel. 
The ViewModel contains a state that represents the properties of the View.

The ViewModel state is represented using a simple kotlin data class with different fields.

## Helper Package or Utils Package:
I created a package named Utils, in order to add some functionalities in order to use them in my App.

## Future Enhancments:
I will change the design of the application and add a new network layer with an interceptor

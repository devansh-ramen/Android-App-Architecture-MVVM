# Android-App-Architecture-MVVM

Google has opened source a sample project: https://github.com/googlesamples/android-architecture/tree/todo-mvvm-live/ as a blueprint of how they want the architecture and design pattern to look like when using LiveData and ViewModel. On our side, we reviewed this project and created a simple Login app based on the same architecture and MVVM design pattern. Our Login app will require two inputs: email & password, which will be validated locally, make an API call to send the request. When API service returns a success/failure response, we will need to update the UI accordingly. The app will support for both portrait and landscape and include client side validation. All of this will be done in a very elegant way using Data Binding, LiveData, ObservableFields and ViewModel.


Walkthrough sample app: https://devanshramen.com/2017/11/19/mvvm-androids-architecture-components-part-1-viewmodel-livedata/

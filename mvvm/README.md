# MVVM
<img width="250px" height="500px" src="/mvvm/sample/sample.gif" />
<br/>
<br/>



## 1. Design Pattern

### Concept
<img width="500px" src="/mvvm/sample/mvvm.png" />

1. View 에 입력이 들어오면 ViewModel 에 명령을 한다.
2. ViewModel 은 필요한 데이터를 Model 에 요청한다.
3. Model 은 ViewModel 에게 요청받은 데이터를 응답한다.
4. ViewModel 은 응답받은 데이터를 가공해서 저장한다.
5. View 는 ViewModel 과의 Data Binding 으로 인해 자동으로 갱신된다.


### View

- View 는 화면에 보여지는 UI 이다.
- 기본적으로 비즈니스 로직을 배제하지만 UI 와 관련된 로직을 수행할 수 있다.


### ViewModel

- View 를 표현하기 위해 만들어진 View 를 위한 모델이라고 보면 된다.
- ViewModel 은 View 에 연결할 데이터와 명령으로 구성되어있으며 변경 알림을 통해서 View 에게 상태 변화를 전달한다.
- 전달받은 상태 변화를 화면에 반영할지는 View 가 선택하도록 한다.
- 명령은 UI 를 통해서 동작하도록 한다.


### Model

- Model 은 UI 에 표시될 데이터와 비즈니스 로직을 담당한다.


### View, ViewModel, Model 의 관계

- ViewModel 은 Model 을 알지만 View 를 알지 못한다.
- View 는 Model 을 알지 못하나 ViewModel 을 알 수 있다.
- View 는 ViewModel 을 옵저빙하고 있다가 상태 변화가 전달되면 화면을 갱신해야 한다.
<br/>
<br/>



## 2. Data Binding

<img width="500px" src="/mvvm/sample/databind.png" />

- MVVM 에서 가장 중요한 핵심으로, View 가 오로지 수동적인 포지션을 취할 수 있고 ViewModel 이 View 의 존재를 알지 못하게 하여 의존성에서 벗어날 수 있게 해준다.
- Data Binding 은 View 와 ViewModel 간의 데이터와 명령을 연결해주는 매개체가 되어 서로의 존재를 명확히 알지 않더라도 다양한 인터랙션을 할 수 있도록 도와준다.
- Model 에서 데이터가 변경되면 ViewModel 을 거쳐서 View 로 전달되도록 하는데, Android 에서는 LiveData 나 RxJava 등을 통해 구현할 수 있다.
<br/>
<br/>



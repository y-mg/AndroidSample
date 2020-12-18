# Paging
<img width="250px" height="500px" src="/paging/sample/sample.gif" />
<br/>
<br/>



## 1. Paging

> 한 번에 필요한 데이터 덩어리(chunck)만 로드하고 표시하는데 도움을 주는 라이브러리이다.


### Paging 구성 요소
<img width="500" src="/paging/sample/component.png">

- DataSource
    - 데이터를 로드하는 역할로, 데이터를 정해진 범위만 불러온 뒤 PagedList 로 로드하는 객체이다.
    - DataSource 는 3가지 종류로, 로딩 관련 로직을 오버라이딩해서 사용한다.

- DataSource.Factory
    - DataSource 객체를 만들어주는 역할을 한다.
    - create() 메소드를 오버라이딩하고, DataSource 객체를 반환한다.
    - Live(Rx)PagedListBuilder 에 등록해주면 자동으로 create() 메소드가 호출된다.

- Live(or Rx)PagedListBuilder
    - DataSource.Factory 와 PagedList.Config 를 가지고 LiveData(or Observable)<PagedList<T>> 를 만드는 Builder 클래스다.

- PagedList
    - DataSource 를 이용해서 데이터를 가져와 UI 에 제공하는 역할을 한다.
    - submitList() 메소드를 호출해서 DataSource 로 데이터를 가져오고, PagedListAdapter 에 데이터가 추가되었음을 알려준다.

- PagedListAdapter
    - PagedList 의 데이터를 RecyclerView 에 보여주기 위한 클래스이다.
    - RecyclerView.Adapter 와의 차이점은 DiffUtil 을 사용해서 새로운 리스트와 이전 리스트를 비교해 변경된 요소들만 업데이트한다.

- DiffUtil.ItemCallback
    - 페이지가 로드될 때 콜백을 받고, 백그라운드 스레드에서 DiffUtil 을 사용해 새로운 리스트와 이전 리스트를 비교한다.
    - areItemsTheSame() 에서 새로운 리스트의 아이템과 이전 리스트의 아이템이 같은지 비교해 같은 경우, areContentsTheSame() 을 호출해 객체의 필드가 같은지 비교한다.

- PageKeyedDataSource
    - 페이지-키 기반의 데이터를 가져올 때 사용하는 DataSource 객체이다.
    - loadInitial() 는 PagedList 가 최초 데이터를 가져올 때 호출된다.
    - loadAfter() 는 PagedList 가 다음 페이지의 데이터를 가져올 때 호출된다.
    - loadBefore() 는 PagedList 가 이전 페이지의 데이터를 가져올 때 호출된다.
<br/>
<br/>



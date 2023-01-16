# :hammer: 진행 중

# server-perform-comparison
Comparison Server Performance.

동기/비동기, 모놀리틱/마이크로서비스의 성능의 차이를 직접 확인해보는 프로젝트.

성능 확인은 **nGrinder**를 사용한다.

여기서 **마이크로서비스는 CQRS 패턴**을 사용한다. 
- Read 서버에는 Memory DB를 사용하는 등 마이크로서비스의 장점인 서비스별 최적화를 진행한다.   
- 동시다발적인 read/write 요청을 모놀리틱 서버 2대와 비교하여 얼마나 차이가 있을지 비교한다.


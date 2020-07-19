# Practice topics
### Configuration
- Add dependencies: Hibernate and MySQL connector
- Create database 
[[create-db.sql](https://github.com/cpulover-practice/hibernate/blob/master/sql/create-db.sql)]
- Configure Hibernate by XML 
[[hibernate.cfg.xml](https://github.com/cpulover-practice/hibernate/blob/master/src/hibernate.cfg.xml)]

### Concepts
- CRUD features
- SessionFactory, Session and Transaction
- Uni-directional and bi-directional relationship
- Cascade types: CascadeType.ALL, CascadeType.PERSIST (save), CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
  - 📌 Use persist() with CascadeType.ALL/PERSIST to save all associated objects.
- Eager loading and Lazy loading; Fetch types: Fetch.LAZY (prefer), Fetch.EAGER
  - To retrieve lazy data, a Hibernate session need to be open (connect to the database).
  - 📌 Resolve Lazy loading issue (because of loading after closing session)
    - Option 1: Call getter method while session is still open 
  [[EagerLazyDemo](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/onetomany/EagerLazyDemo.java)]
    - Option 2: Use Hibernate query with HQL [hibernate.query.Query]
  [[FetchJoinDemo](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/onetomany/FetchJoinDemo.java)]


### Entity Class
- Add no-args constructor (required by Hibernate)
- 📌 Add convenience methods for bi-directional one/many-to-many relationship 
[[Instructor](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/entity/Instructor.java)]
- *__@Entity__* [javax.persistence]
- *__@Id__* and *__@GeneratedValue__* (prefer GenerationType.IDENTITY)
- *__@Table:__* map to table in the database [javax.persistence]
- *__@Column:__* map to column of the table
- *__@OneToOne__* 
[[Instructor](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/entity/Instructor.java)
/[InstructorDetail](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/entity/InstructorDetail.java)]
  - Uni-directional (use *__@JoinColumn__*) and bi-directional (add *`mappedBy`* to *__@OneToOne__*)
  -  *__@JoinColum:__* used in the entity class containing the foreign key.
  - *`mappedBy`* attribute: used in the class entity which does not contain the foreign key.
- *__@OneToMany__* 
[[Course](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/entity/Course.java)
/[Review](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/entity/Review.java)]
  - Uni-directional: *__@JoinColumn__* used in the class entity which does not contain the foreign key.
- *__@ManyToMany__* 
[[Student](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/entity/Student.java)
/[Course](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/entity/Course.java)]
  - *`fetch`* should be FetchType.LAZY
  - *`cascade`* should omit CascadeType.REMOVE
  - *__@JoinTable__* with *`joinColumns`* and *`inverseJoinColumns`* for each foreign key

### App Flow
[[CreateDemo](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/onetoone/CreateDemo.java)]
1. Create a Session Factory including all the entities
2. Create a Session
3. Start a Transaction
4. Perfrom CRUD (save/permit if create or update entities)
5. Commit the Transaction
6. Handle connection leak issue: catch error and close Session.
7. Close the Factory



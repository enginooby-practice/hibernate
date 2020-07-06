# List of practiced topics
### Configuration and concepts
- Configure Hibernate XML file 
[[hibernate.cfg.xml](https://github.com/cpulover-practice/hibernate/blob/master/src/hibernate.cfg.xml)]
- CRUD features
- SessionFactory and Session
- Start and commit a Transaction
- Cascade types: CascadeType.ALL, CascadeType.PERSIST (save), CascadeType.REMOVE
- Eager loading and Lazy loading; Fetch types: Fetch.LAZY, Fetch.EAGER

### Entity class
- Add no-args constructor for entity class.
- Add convenience methods for bi-directional one/many-to-tomany relationship.
- *__@Entity__* [javax.persistence]
- *__@Id__*
- *__@GeneratedValue__*
  - GenerationType.IDENTITY
- *__@Table:__* map to table in the database [javax.persistence]
- *__@Column:__* map to column of the table
- *__@OneToOne__* 
[[Instructor](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/entity/Instructor.java)
/[InstructorDetail](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/entity/InstructorDetail.java)]
  - Uni-directional (use *__@JoinColumn__*) and bi-directional (use *`mappedBy`*)
  - *`mappedBy`* attribute: used in the class entity which does not contain the foreign key.
  -  *__@JoinColum:__* used in the table class entity containing the foreign key.
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

### Notes and Tips
- Handle connection leak issue: catch error and close session.
- Use persist() with CascadeType.ALL/PERSIST to save all associated objects.
- Prefer Lazy loading then Eager loading.
- To retrieve lazy data, a Hibernate session need to be open (connect to the database).
- Resolve Lazy loading issue (because of loading after closing session)
  - Option 1: Call getter method while session is still open 
  [[EagerLazyDemo](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/onetomany/EagerLazyDemo.java)]
  - Option 2: Use Hibernate query with HQL [hibernate.query.Query]
  [[FetchJoinDemo](https://github.com/cpulover-practice/hibernate/blob/master/src/com/cpulover/hibernate/onetomany/FetchJoinDemo.java)]

### !
- (?) Questionnable
- [] Import, Class, Resource

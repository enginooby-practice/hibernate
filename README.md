# List of practiced topics
- Configure hibernate xml file (hibernate.cfg.xml)
- CRUD features
- SessionFactory and Session
- Start and commit a Transaction
- Add no-arg constructor for entity class.
- @Entity [javax.persistence]
- @Id
- @GeneratedValue
  - GenerationType.IDENTITY
- @Table: map to table in the database [javax.persistence]
- @Column: map to column of the table
- @OneToOne [Instructor/InstructorDetail]
  - Uni-directional (use @JoinColumn) and bi-directional (use *`mappedBy`*)
  - *`mappedBy`* attribute: used in the class entity which does not contain the foreign key.
  -  @JoinColum: used in the table class entity containing the foreign key.
- @OneToMany [Course/Review]
  - Uni-directional: @JoinColumn used in the class entity which does not contain the foreign key.
- @ManyToMany [Student/Course]
  - *`fetch`* should be FetchType.LAZY
  - *`cascade`* should omit CascadeType.REMOVE
  - @JoinTable with *`joinColumns`* and *`inverseJoinColumns`* for each foreign key
- Cascade types: CascadeType.ALL, CascadeType.PERSIST (save), CascadeType.REMOVE
- Eager loading and Lazy loading; Fetch types: Fetch.LAZY, Fetch.EAGER


### Notes and Tips
- Handle connection leak issue: catch error and close session.
- Add convenience methods for bi-directional one/many-to-tomany relationship
- Use persist() with CascadeType.ALL/PERSIST to save all associated objects.
- Prefer Lazy loading then Eager loading.
- To retrieve lazy data, a Hibernate session need to be open (connect to the database).
- Resolve Lazy loading issue (because of loading after closing session)
  - Option 1: Call getter method while session is still open [EagerLazyDemo]
  - Option 2: Use Hibernate query with HQL [hibernate.query.Query][FetchJoinDemo]

### !
- (?) Questionnable
- [ ] Import, Class

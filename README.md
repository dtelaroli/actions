# VRaptor Actions

Não use Generics nos seus Controllers e Daos, use Dependency Injection.

Este projeto permite que você separe a logica de cada action dos seus controllers em classes, sem tornar ilegível seu construtor. Utilizando o princípio de DI é possível você criar classes injetáveis, fáceis de testar diretamente dentro do método da ação, através da interface `Act`.

A API possui duas funcionalidades básicas, Act e Db. A primeira permite você injetar suas classes que implementam Action, que tem a finalidade de uso direto no controller e a segunda classes que implementam Database, que são para serem usadas dentro das actions, mas podem ser usadas diretamente no controller.

## Instalação

Configure a sua aplicação para funcionar normalmente com o [VRaptor](http://vraptor.org) e adicione as dependências do VRaptor Actions.

```xml
<dependency>
	<groupId>br.com.caelum.vraptor</groupId>
	<artifactId>actions-core</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

A API de persistência é separada para permitir diferentes implementações, adicione a implementação desejada:

### Ebean

https://github.com/dtelaroli/actions/tree/master/actions-db-ebean

### Hibernate

Not implemented yet

### JPA

Not implemented yet

## Action

### Ações implementadas.

- ListAction: `Actions.list()`
- LoadAction: `Actions.load()`
- PaginationAction: `Actions.pagination()`
- PersistAction: `Actions.persist()`
- DeleteAction: `Actions.delete()`

### Criando as suas Actions

Para criar uma action basta criar uma classe que implementa a interface `Action`.

#### Action

```Java
class MyAction implements Action {
	
	public void myMethod() {
	
	}
	
}
```

#### Uso

```Java
act.as(MyAction.class).myMethod();
```

### CRUD de Exemplo

```Java
@Controller @Path("/user")
public class UserController {

	private final Act act;

	/**
	 * @deprecated
	 */
	protected UserController() {
		this(null);
	}

	@Inject
	public UserController(Act act) {
		this.act = act;
	}

	public List<User> index() {
		return act.as(list()).all(User.class);
	}
	
	public Page<User> paginate() {
		return paginate(1);
	}
	
	@Get("/paginate/{page}")
	public Page<User> paginate(int page) {
		return act.as(pagination()).page(page).paginate(User.class);
	}
	
	@Get("/{id}")
	public User view(Long id) {
		return act.as(load()).by(User.class, id);
	}
	
	public void add() {
	}
	
	@Get("/{id}/edit")
	public User edit(Long id) {
		return act.as(load()).by(User.class, id);
	}
	
	@Post
	public void insert(@NotNull @Valid User user) throws Exception {
		onErrorRedirect();
		act.as(persist()).insert(user).andRedirectTo(this).view(user.getId());
		
	}

	private void onErrorRedirect() {
		act.validator().onErrorUse(referer()).redirect();
	}
	
	@Put("/{id}")
	public void update(@NotNull @Valid User user) throws Exception {
		onErrorRedirect();
		act.as(persist()).update(user).andRedirectTo(this).view(user.getId());
	}
	
	@Delete("/{id}")
	public void remove(Long id) {
		act.as(delete()).by(User.class, id).andRedirectTo(this).paginate();
	}
	
}
```

## Database

### Componentes

Podem ser injetados através da interface `Db`.

```Java
- FindAction: `Databases.find()`
- PersistDb: `Databases.persist()`
- DeleteDb: `Databases.delete()`
```

### Criando as seus Databases

Para criar um database basta criar uma classe que implementa a interface `Database`.

#### Action

```Java
class MyDb implements Database {
	
	public void myMethod() {
	
	}
	
}
```

#### Uso

```Java
class MyAction implements Action {

	/**
	 * @deprecated
	 */
	protected MyAction() {
		this(null);
	}

	@Inject
	public MyAction(Db db) {
		this.db = db;
	}
	
	public void myMethod() {
		db.use(MyDb.class).myMethod();
	}

}
```

## Tests

### Mocks

Para valicitar os testes de controllers e actions foram criados estes mocks:

```Java
MockAct
MockDb
```

### DbUnit

Para facilitar os testes de integração foi criado este [DbUnit Helper](https://github.com/dtelaroli/dbunit)
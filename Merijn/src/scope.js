export class Scope {
	constructor(parent_scope) {
		this.parent_scope = parent_scope || null;
		this.scope = {};
	}
	has (name) {
		return name in this.scope || this.parent_scope !== null && this.parent_scope.has(name);
	}
	get (name) {
		if (name in this.scope) {
			return this.scope[name];
		}

		if (this.parent_scope !== null) {
			return this.parent_scope.get(name);
		}

		throw new Error("No name `" + name + "` in scope");
	}
	set (name, type) {
		if (this.has(name)) {
			throw new Error("Name `" + name + "` already defined");
		}
		this.scope[name] = type;
	}
}
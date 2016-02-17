import * as ast from 'src/ast';
import { Observable } from 'src/observable';

export class InputElementWidget extends Observable {
	constructor(inputElement) {
		super();
		this.inputElement = inputElement;
		this.inputElement.addEventListener('change', () => this.notifyObservers());
	}
	getValue() {
		return this.inputElement.value;
	}
	setValue(new_value) {
		this.inputElement.value = new_value;
	}
	getType() {
		throw new Error("Override in subclasses");
	}
}

export class BooleanCheckboxWidget extends InputElementWidget {
	constructor(elementFactory) {
		super();
		this.elementFactory = elementFactory;
	}
	getValue() {
		return this.inputElement.checked;
	}
	setValue(new_value) {
		this.inputElement.checked = !!new_value;
	}
	getType() {
		return ast.TYPE_BOOLEAN;
	}
	static render(containerElement) {
		let inputElement = this.elementFactory.createElement('input');
		inputElement.setAttribute('type', 'checkbox');
		containerElement.appendChild(inputElement);
		return new BooleanCheckboxWidget(inputElement);
	}
}

export class StringInputWidget extends InputElementWidget {
	getType() {
		return ast.TYPE_STRING;
	}
	static render(elementFactory, containerElement) {
		let inputElement = elementFactory.createElement('input');
		inputElement.setAttribute('type', 'text');
		containerElement.appendChild(inputElement);
		return new StringInputWidget(inputElement);
	}
}

export class IntegerInputWidget extends InputElementWidget {
	getValue() {
		return parseInt(this.inputElement.value, 10);
	}
	setValue(new_value) {
		this.inputElement.value = "" + new_value;
	}
	getType() {
		return ast.TYPE_INTEGER;
	}
	static render(elementFactory, containerElement) {
		let inputElement = elementFactory.createElement('input');
		inputElement.setAttribute('type', 'number');
		inputElement.setAttribute('step', 1);
		containerElement.appendChild(inputElement);
		return new IntegerInputWidget(inputElement);
	}
}

export class FloatInputWidget extends InputElementWidget {
	getType() {
		return ast.TYPE_FLOAT;
	}
	static render(elementFactory, containerElement) {
		let inputElement = elementFactory.createElement('input');
		inputElement.setAttribute('type', 'number');
		containerElement.appendChild(inputElement);
		return new FloatInputWidget(inputElement);
	}
}

export class WidgetFactory {
	constructor(elementFactory) {
		this.elementFactory = elementFactory;
	}
	renderWidget(type, containerElement, readOnly, initialValue) {
		switch (type) {
			case ast.TYPE_BOOLEAN:
				return BooleanCheckboxWidget.render(this.elementFactory, containerElement, readOnly, initialValue);
			case ast.TYPE_STRING:
				return StringInputWidget.render(this.elementFactory, containerElement, readOnly, initialValue);
			case ast.TYPE_INTEGER:
				return IntegerInputWidget.render(this.elementFactory, containerElement, readOnly, initialValue);
			case ast.TYPE_FLOAT:
				return FloatInputWidget.render(this.elementFactory, containerElement, readOnly, initialValue);
			default:
				throw new Error("Unknown type `" + type + "`");
		}
	}
}
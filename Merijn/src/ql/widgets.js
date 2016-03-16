import * as _ from 'lodash';
import * as values from 'src/ql/values';
import { TypeReceiver } from 'src/ql/types';
import { Observable } from 'src/ql/observable';

class Widget extends Observable {
	getValue() {
		throw new Error("Override in subclasses");
	}
	setValue() {
		throw new Error("Override in subclasses");
	}
}

class InputWidget extends Widget {
	constructor(inputElement) {
		super();
		this.inputElement = inputElement;
		this.inputElement.onchange = () => {
			this.notifyListeners();
		};
	}
	static renderInputElement(elementFactory, containerElement, attributes) {
		let inputElement = elementFactory.createElement('input');
		_.forEach(attributes, function (value, key) {
			inputElement.setAttribute(key, value);
		});
		containerElement.appendChild(inputElement);
		return inputElement;
	}
}

class StringInputWidget extends InputWidget {
	getValue() {
		return new values.StringValue(this.inputElement.value);
	}
	setValue(value) {
		this.inputElement.value = value.toString();
	}
	static render(elementFactory, containerElement) {
		return new StringInputWidget(InputWidget.renderInputElement(elementFactory, containerElement, {
			'type': 'text'
		}));
	}
}

class BooleanCheckboxWidget extends InputWidget {
	getValue() {
		return new values.BooleanValue(this.inputElement.checked);
	}
	setValue(value) {
		this.inputElement.checked = value.equals(new values.BooleanValue(true));
	}
	static render(elementFactory, containerElement) {
		return new BooleanCheckboxWidget(InputWidget.renderInputElement(elementFactory, containerElement, {
			'type': 'checkbox'
		}));
	}
}

class IntegerInputWidget extends InputWidget {
	getValue() {
		return values.IntegerValue.fromString(this.inputElement.value);
	}
	setValue(value) {
		this.inputElement.value = value.toString();
	}
	static render(elementFactory, containerElement) {
		return new IntegerInputWidget(InputWidget.renderInputElement(elementFactory, containerElement, {
			'type': 'number',
			'step': 1
		}));
	}
}

export class WidgetRenderer extends TypeReceiver {
	constructor(elementFactory) {
		super();
		this.elementFactory = elementFactory;
	}
	render(type, containerElement, disabled) {
		return type.dispatch(this, containerElement, disabled);
	}
	receiveBoolean(type, containerElement, disabled) {
		return BooleanCheckboxWidget.render(this.elementFactory, containerElement, disabled);
	}
	receiveString(type, containerElement, disabled) {
		return StringInputWidget.render(this.elementFactory, containerElement, disabled);
	}
	receiveInteger(type, containerElement, disabled) {
		return IntegerInputWidget.render(this.elementFactory, containerElement, disabled);
	}
	receiveFloat() {
		throw new Error("TODO");
	}
	receiveMoney() {
		throw new Error("TODO");
	}
}
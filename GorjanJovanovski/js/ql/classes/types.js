class NumberType {
	parseValue(value) {
		return parseInt(value);
	}

	defaultValue() {
		return 0;
	}

	toString() {
		return 'number';
	}

	getTypeString() {
		return "integer";
	}
}

class DecimalType {
	parseValue(value) {
		return parseFloat(value);
	}

	defaultValue() {
		return 0.0;
	}

	toString() {
		return 'number';
	}

	getTypeString() {
		return "decimal";
	}
}

class MoneyType extends DecimalType {
	getTypeString() {
		return "money";
	}
}

class CurrencyType extends DecimalType {
	getTypeString() {
		return "currency";
	}
}

class FloatType {
	getTypeString() {
		return "float";
	}
}

class BooleanType {
	parseValue(value) {
		if (typeof value === 'boolean')
			return value;
		return value === "true";
	}

	defaultValue() {
		return false;
	}

	toString() {
		return 'boolean';
	}

	getTypeString() {
		return "boolean";
	}
}

class StringType {
	parseValue(value) {
		return value + "";
	}

	defaultValue() {
		return "";
	}

	toString() {
		return 'string';
	}

	getTypeString() {
		return "string";
	}
}

class DateType extends StringType {

	getTypeString() {
		return "date";
	}
}
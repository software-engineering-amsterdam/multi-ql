/*global describe, it */
import { parse } from 'src/parser';

describe('Parser', ()=>{
	"use strict";

	it('parses a form', ()=>{
		var f = `
			Form {
				"foo" bar boolean;
				"bar" baz money;
				if (bar >= 11.2) {

				} else {

				}
			}
		`;
		parse(f);
	});
});
package gui

import (
	"errors"
	log "github.com/Sirupsen/logrus"
	"github.com/mattn/go-gtk/gtk"
	"ql/ast/expr"
	"ql/ast/vari"
	"ql/interfaces"
	"strconv"
)

type GUIQuestion struct {
	Label      *gtk.Label
	Element    gtk.IWidget
	ErrorLabel *gtk.Label
}

func CreateGUIQuestion(label string, questionType interfaces.VarType, callback func(interface{}, error)) GUIQuestion {
	questionLabel := CreateLabel(label)
	questionElement := createQuestionElement(questionType, callback)
	errorLabel := CreateLabel("")

	return GUIQuestion{questionLabel, questionElement, errorLabel}
}

func (g GUIQuestion) ChangeElementText(newText string) {
	log.WithFields(log.Fields{"oldLabelText": g.Label.GetText(), "newLabelText": newText}).Debug("Changing text of element")
	g.Element.(*gtk.Entry).SetText(newText)
}

func (g GUIQuestion) ChangeErrorLabelText(newText string) {
	g.ErrorLabel.SetText(newText)
}

func createQuestionElement(questionType interfaces.VarType, callback func(interface{}, error)) gtk.IWidget {
	var GTKEntity gtk.IWidget

	switch questionType.(type) {
	case vari.BoolType:
		checkbox := CreateCheckboxConditional()
		checkbox.Connect("clicked", func() {
			log.WithFields(log.Fields{"value": checkbox.GetActive()}).Debug("Checkbox value changed")
			callback(expr.BoolLit{checkbox.GetActive()}, nil)
		})
		GTKEntity = checkbox
	case vari.StringType:
		inputField := CreateInputTextField("")
		inputField.Connect("changed", func() {
			inputText := inputField.GetText()

			log.WithFields(log.Fields{"value": inputText}).Debug("Input text value changed")

			callback(expr.StrLit{inputText}, nil) // TODO check if really is string
		})
		GTKEntity = inputField
	case vari.IntType:
		inputField := CreateInputTextField("")
		inputField.Connect("changed", func() {
			inputText := inputField.GetText()
			log.WithFields(log.Fields{"value": inputText}).Debug("Input text value changed")

			inputTextAsInt, err := strconv.Atoi(inputText)
			if inputText == "" {
				callback(questionType.GetDefaultValue(), nil)
				return
			} else if err != nil {
				log.Warn("Could not convert input text string to int")
				callback(nil, err)
				return
			}

			if callback != nil {
				callback(expr.IntLit{inputTextAsInt}, nil)
			}
		})
		GTKEntity = inputField
	default:
		errors.New("Unknown question type, can not create correct GTK object")
	}

	return GTKEntity
}

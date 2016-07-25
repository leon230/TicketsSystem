//package ch.makery.address.util;
//
//StringConverter converter = new StringConverter<LocalDate>() {
//        DateTimeFormatter dateFormatter =
//        DateTimeFormatter.ofPattern(pattern);
//@Override
//public String toString(LocalDate date) {
//        if (date != null) {
//        return dateFormatter.format(date);
//        } else {
//        return "";
//        }
//        }
//@Override
//public LocalDate fromString(String string) {
//        if (string != null && !string.isEmpty()) {
//        return LocalDate.parse(string, dateFormatter);
//        } else {
//        return null;
//        }
//        }
//        };
//        checkInDatePicker.setConverter(converter);
//        checkInDatePicker.setPromptText(pattern.toLowerCase());
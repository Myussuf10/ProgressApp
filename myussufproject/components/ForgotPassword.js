import React from 'react';
import {
  KeyboardAvoidingView,
  StyleSheet,
  Text,
  View,
  TextInput,
  TouchableOpacity
} from 'react-native';

const ForgotPassword = ({navigation}) => {
  return (
    <KeyboardAvoidingView style={styles.container}>
      <Text style={{ fontSize: 30, marginBottom: 50 }}>Admin Details</Text>
      <TextInput
        placeholder="First Name"
        style={styles.txt}
        placeholderTextColor="grey" />
      <TextInput
        placeholder="Last Name"
        style={styles.txt}
        placeholderTextColor="grey" />
      <TextInput
        placeholder="Email"
        style={styles.txt}
        placeholderTextColor="grey" />
      <TextInput
        placeholder="Password"
        placeholderTextColor="grey"
        style={styles.txt} />
      <TouchableOpacity title="ForgotPassword" onPress style={styles.button}>
        <Text style={{ textAlign: 'center', fontSize: 15 }}> Register </Text>
      </TouchableOpacity>
    </KeyboardAvoidingView>
  );
};

export default ForgotPassword;

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    flex: 1,
    justifyContent: 'center',
    backgroundColor: '#B4B4B4',
  },
  txt: {
    textAlign: 'center',
    color: 'black',
    borderRadius: 5,
    backgroundColor: 'white',
    fontSize: 15,
    borderWidth: 0.5,
    marginTop: 0.1,
    margin: 10,
    width: 300,
  },
  button: {
    backgroundColor: 'grey',
    borderRadius: 5,
    padding: 10,
    marginTop: 10,
    width: 220,
  },
});

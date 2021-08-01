import React, {useState} from 'react';
import type {Node} from 'react';
import {
  KeyboardAvoidingView,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  useColorScheme,
  View,
  Button,
} from 'react-native';
import ForgotPassword from './ForgotPassword';

const Login = ({navigation}) => {
  const [email, setEmail] = useState(' ');
  const [password, setPassword] = useState(' ');

  const login = () => {};
  return (
    <KeyboardAvoidingView behavior="height" style={styles.background}>
      <View style={styles.inputContainer}>
        <TextInput
          placeholder="Email"
          style={styles.txt2}
          placeholderTextColor="black"
          onChangeText={val => setEmail(val)}
        />
        <TextInput
          placeholder="Password"
          placeholderTextColor="black"
          style={styles.txt2}
          onChangeText={val => setPassword(val)}
        />
      </View>
      <TouchableOpacity title="LogIn" onPress={login} style={styles.button}>
        <Text style={styles.txt}> Login </Text>
      </TouchableOpacity>
      <TouchableOpacity
        title="ForgotPassword"
        onPress={() => {
          navigation.navigate('ForgotPassword');
        }}
        style={styles.button}>
        <Text style={{"textAlign": 'center', "fontSize": 15}}> Forgot Password </Text>
      </TouchableOpacity>
    </KeyboardAvoidingView>
  );
};

const styles = StyleSheet.create({
  inputContainer: {
    width: 300,
  },
  background: {
    alignItems: 'center',
    flex: 1,
    justifyContent: 'center',
    backgroundColor: '#B4B4B4',
  },
  button: {
    backgroundColor: 'grey',
    borderRadius: 5,
    padding: 10,
    marginTop: 10,
    width: 220,
  },

  txt: {
    textAlign: 'center',
    fontSize: 15,
  },
  txt2: {
    textAlign: 'center',
    color: 'white',
    borderRadius: 5,
    backgroundColor: 'white',
    fontSize: 15,
    borderWidth: 0.5,
    marginTop: 0.1,
    margin: 10,
  },
});

export default Login;

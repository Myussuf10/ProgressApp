import React, { useState, useContext } from 'react';
import {
  requireNativeComponent,
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
  Alert,
} from 'react-native';
import  {AuthContext}  from './store/AuthContext';
import { login } from './util/http';
import LoadingOverlay from './util/LoadingSpinner';

const Login = ({ navigation }) => {
  const [email, setEmail] = useState(' ');
  const [password, setPassword] = useState(' ');
  const [isAuthenticating, setIsAuthenticating] = useState(false);
  const authCtx = useContext(AuthContext);

  async function handleSubmit(email,password) {
    console.log(email, password)
    setIsAuthenticating(true)
    try {
       const response = await login(email, password) 
      console.log(response);
      authCtx.authenticate(response.accestoken, response.userrole, email);
    } catch (error) {
      Alert.alert('Authentication Failed', 'Please try again')
      console.log(error)
      setIsAuthenticating(false)
    }

    return () =>{setIsAuthenticating(false)}

  };
  if(isAuthenticating){
    return <LoadingOverlay/>
  }
  return (
    <KeyboardAvoidingView behavior="height" style={styles.screen}>
      <View style={styles.background}>
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
            secureTextEntry
            style={styles.txt2}
            onChangeText={val => setPassword(val)}
          />
        </View>
        <TouchableOpacity title="LogIn" onPress={() => { handleSubmit(email, password) }} style={styles.button}>
          <Text style={styles.txt}> Login </Text>
        </TouchableOpacity>

        <TouchableOpacity
          title="Admin"
          onPress={() => {
            null
          }}
          style={styles.button}>
          <Text style={styles.txt}> Forgot Password </Text>
        </TouchableOpacity>

      </View>
    </KeyboardAvoidingView>
  );
};

const styles = StyleSheet.create({
  inputContainer: {
    width: 300,
  },
  screen: {
    flex: 1,
  },
  background: {
    alignItems: 'center',
    flex: 1,
    justifyContent: 'center',
    backgroundColor: '#B4B4B4',
    padding: 10
  },
  button: {
    backgroundColor: '#608d56',
    borderRadius: 5,
    padding: 10,
    marginTop: 10,
    width: 220,
  },
  button2: {

  },

  txt: {
    textAlign: 'center',
    fontSize: 15,
    color: "white",
  },
  txt2: {
    textAlign: 'center',
    color: 'black',
    borderRadius: 5,
    backgroundColor: 'white',
    fontSize: 15,
    borderWidth: 0.5,
    marginTop: 0.1,
    margin: 10,
  },
});

export default Login;
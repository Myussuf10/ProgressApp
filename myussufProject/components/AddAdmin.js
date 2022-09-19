import { View, Text, StyleSheet, KeyboardAvoidingView, TouchableOpacity } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import Input from './Forms/Input'
import { addAdministrator, storeParents } from './util/http';
import SignUp from './Forms/SignUp';
import { AuthContext } from './store/AuthContext';

const AddAdmin =({navigation}) => {
  const [inputValues, setInputValues] = useState({
    firstname: '',
    lastname: '',
    email: '',
    password: ""
  });
  const authCtx = useContext(AuthContext)

  const inputHandler=(inputId, enteredValue)=>{
    setInputValues((currentInput)=>{
      return{
        ...currentInput,
        [inputId]:enteredValue,
        password: "admin123"
      };
    });
  }

  const Assign =()=>{
    console.log(inputValues)
    addAdministrator(inputValues, authCtx.token);
    navigation.pop();
  }


  return (
    <KeyboardAvoidingView behavior='padding' style={styles.screen}>
    <Text style={styles.header}>Signing Up</Text>
      <Input label="First Name" textInputConfig={{
        placeholder: 'First Name',
         onChangeText: inputHandler.bind(this,'firstname'),
          value: inputValues.firstname,
      }} />
      <Input label="Last Name" textInputConfig={{
        placeholder: 'Last Name', 
        onChangeText: inputHandler.bind(this,'lastname'),
         value: inputValues.lastname,
      }} />
      <Input label="Email" textInputConfig={{
        placeholder: 'Email', 
        onChangeText: inputHandler.bind(this,'email'),
         value: inputValues.email,
      }} />
      <TouchableOpacity
        title="Submit"
        onPress={() => {Assign()

        }}
        style={styles.button}>
        <Text style={{
          textAlign: 'center',
          fontSize: 20,
          color: "white",
        }}> Submit </Text>
      </TouchableOpacity>
    </KeyboardAvoidingView>
  )
}

export default AddAdmin;


const styles = StyleSheet.create({
    screen: { flex: 1, backgroundColor: '#B4B4B4' },
    header: {
        fontSize: 22,
        textAlign: 'center',
        margin: 8
      },
      button: {
        backgroundColor: '#608d56',
        borderRadius: 5,
        padding: 10,
        marginTop: 10,
        },
  
  })
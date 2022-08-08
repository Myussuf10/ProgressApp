import { View, Text, KeyboardAvoidingView, StyleSheet } from 'react-native'
import React from 'react'
import { useState } from 'react';
import Input from './Forms/Input';
import { TouchableOpacity } from 'react-native-gesture-handler';
import { signUpTeacher } from './util/http';
import Admin from './Admin';

const AddTeacher = ({navigation}) => {
  const [inputValues, setInputValues] = useState({
    firstname: '',
    lastname: '',
    email: '',
    password: ""
  });

  const inputHandler=(inputId, enteredValue)=>{
    setInputValues((currentInput)=>{
      return{
        ...currentInput,
        [inputId]:enteredValue,
      };
    });
  }

  const Assign =()=>{
    const y = Math.random().toString(36).substring(2,7);
    setInputValues((x)=>{
      return{
      ...x,
       password:y,
    }});
    signUpTeacher(inputValues);
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

export default AddTeacher


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
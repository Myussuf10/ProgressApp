import { Text } from '@rneui/themed'
import React from 'react'
import { useState } from 'react'
import { KeyboardAvoidingView, StyleSheet } from 'react-native'
import Input from './Forms/Input'

function NewSubject({navigation,title}) {
  const [subject, setSubject] = useState("")

  
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

export default NewSubject

const styles = StyleSheet.create({
  row:{
    alignItems: 'center',
    justifyContent: 'center',  
    flexDirection: 'row',
    marginTop: 100
    
  },
  
  background: {
    alignItems: 'center',
    justifyContent: 'center',  
  
  },
  txt: {
      textAlign: 'center',
      fontSize: 20,
      color: "white",
    },button: {
      backgroundColor: '#608d56',
      borderRadius: 5,
      padding: 10,
      width: 150,
      height: 60,
      margin: 5
    },
})
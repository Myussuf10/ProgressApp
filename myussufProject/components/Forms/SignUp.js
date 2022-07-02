import { StyleSheet, Text, View, TouchableOpacity, KeyboardAvoidingView } from 'react-native'
import React from 'react'
import Input from './Input'
import Parent from '../Parent'
import { Button } from '@rneui/themed/dist/Button'



const SignUp = () => {

   return (
    <KeyboardAvoidingView behavior='padding' style={styles.screen}>
    <Text style={styles.header}>Signing Up</Text>
      <Input label="First Name" textInputConfig={{
        placeholder: 'First Name',
        onChangeText: ()=>{}
      }}/>
      <Input label="Last Name"textInputConfig={{
        placeholder: 'Last Name',
        onChangeText: ()=>{}
        }}/>
    
      <Input label="DOB  " textInputConfig={{
        keyboardType: 'number-pad',
        placeholder: 'DD/MM/YYYY',
      }}/>
      <Input label="School  " textInputConfig={{
        placeholder: 'School',
      }}/>
      <View style= {styles.dropdown}>
      <Parent/>
      <Parent/>
      </View>
      <Button/>
    </KeyboardAvoidingView>
  )
}


export default SignUp;


const styles = StyleSheet.create({
  header:{
    fontSize: 22,
    textAlign: 'center'
  },
  dropdown:{
    display: 'flex',
    flexDirection: 'row'
  },
  screen:{flex:1, backgroundColor: '#B4B4B4'},
  background:{
    flex:1, 
    justifyContent:'center',
    color:'white',
  },
   
})
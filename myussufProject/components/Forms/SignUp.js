import { StyleSheet, Text, View, TouchableOpacity, KeyboardAvoidingView } from 'react-native'
import React from 'react'
import Input from './Input'
import Parent from '../Parent'
import { Button } from '@rneui/themed/dist/Button'
import { useState } from 'react'
import Subject from '../Subject'
import { CheckBox } from '@rneui/themed'



const SignUp = () => {
  const [students, setStudents] = useState({});

  function Assign() {
    console.log(students);
  }


  return (
    <KeyboardAvoidingView behavior='padding' style={styles.screen}>
      <Text style={styles.header}>Signing Up</Text>
      <Input label="First Name" textInputConfig={{
        placeholder: 'First Name',
        onChangeText: (val) => {students.firstname = val }
      }} />
      <Input label="Last Name" textInputConfig={{
        placeholder: 'Last Name',
        onChangeText: (val) => { students.lastname = val}
      }} />

      <Input label="DOB  " textInputConfig={{
        keyboardType: 'number-pad',
        placeholder: 'DD/MM/YY',
        onChangeText: (val)=>{students.dob = new Date(val)}
      }} />
      <Input label="School  " textInputConfig={{
        placeholder: 'School',
        onChangeText: val=>{students.school=val}
      }} />
      <View style={styles.dropdown}>
        <Parent />
        <Subject/>
      </View>
      <View>
      {}
        <CheckBox title='Click here' checked={true}/>
      </View>
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


export default SignUp;


const styles = StyleSheet.create({
  header: {
    fontSize: 22,
    textAlign: 'center',
  },
  button: {
    backgroundColor: '#608d56',
    borderRadius: 5,
    padding: 10,
    marginTop: 10,
    },
  dropdown: {
    display: 'flex',
    flexDirection: 'row',
  },
  screen: { flex: 1, backgroundColor: '#B4B4B4' },
  background: {
    flex: 1,
    justifyContent: 'center',
    color: 'white',
  },

})
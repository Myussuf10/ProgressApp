import { StyleSheet, Text, View, TouchableOpacity, KeyboardAvoidingView} from 'react-native'
import React from 'react'
import Input from './Input'
import Parent from '../Parent'
import { Button } from '@rneui/themed/dist/Button'
import { useState } from 'react'
import Subject from '../Subject'


const SignUp = ({navigation}) => {
  const [student, setStudent] = useState({
    firstname: '',
    lastname: '',
    school: '',
    dob:'',
    parent:'',
  });
  const [parent, setParent] = useState("")

  const [subjects, setSub] = useState({
    Maths: false,
    Science: false,
    English: false
  });

  const inputHandler=(inputId, enteredValue)=>{
    setStudent((currentInput)=>{
      return{
        ...currentInput,
        [inputId]:enteredValue,
      };
    });
  }

  function Assign() {
    console.log(subjects);
  }
 
  

  return (
    <KeyboardAvoidingView behavior='padding' style={styles.screen}>
      <Text style={styles.header}>Signing Up</Text>
      <Input label="First Name" textInputConfig={{
        placeholder: 'First Name',
        onChangeText: inputHandler.bind(this,'firstname'),
        value: student.firstname }} />
      <Input label="Last Name" textInputConfig={{
        placeholder: 'Last Name', 
        onChangeText: inputHandler.bind(this,'lastname'),
        value: student.lastname }} />

      <Input label="DOB  " textInputConfig={{
        keyboardType: 'number-pad',
        placeholder: 'DD/MM/YY',
        onChangeText: inputHandler.bind(this,'dob'),
        value: student.dob }} />
      <Input label="School  " textInputConfig={{
        placeholder: 'School',
        onChangeText: inputHandler.bind(this,'school'),
        value: student.school }} />
      <View style={styles.dropdown}>
        <Parent setParent={inputHandler.bind(this,'parent')} />
        <TouchableOpacity
        title="AddParent"
        onPress={() => {navigation.navigate('AddParent')

        }}
        style={styles.parentbutton}>
        <Text style={{
          textAlign: 'center',
          fontSize: 20,
          color: "black",
        }}> Add Parent </Text>
      </TouchableOpacity>
        
       
      </View>
      <Text style={styles.header}>Select Subjects</Text>

      <View style={styles.checkboxWrapper}>  
      
                
      {/* <Subject label="Maths" setSub={inputHandler.bind(this,true)}/>
      <Subject label= "Science" setSub={setSub}/>
      <Subject label= "English" setSub={setSub}/> */}

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
    margin: 8
  },
  parentbutton:{
    backgroundColor: '#EFEFEF',
    padding: 12,
    width: 190
  },
  button: {
    backgroundColor: '#608d56',
    borderRadius: 5,
    padding: 10,
    marginTop: 10,
    },
    checkboxWrapper: {
      alignItems: 'center',
      flexDirection: 'row',
      paddingVertical: 5,
      flexWrap: 'wrap',
      padding: 15,
    },
  dropdown: {
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center'
  },
  screen: { flex: 1, backgroundColor: '#B4B4B4' },
    text:{
    margin: 8,
  }

})
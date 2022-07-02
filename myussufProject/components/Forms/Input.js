import { Text, View, StyleSheet } from "react-native";
import { TextInput } from "react-native-gesture-handler";
import React from "react";

function Input ({label, textInputConfig}){
    return(
        <View style={styles.inputContainer}>
            <Text style={styles.label}>{label}</Text>
            <TextInput style={styles.input} {...textInputConfig}/>
        </View>
    )
}

export default Input

const styles = StyleSheet.create({
    inputContainer: {
        marginHorizontal: 4,
        marginVertical: 8
    },
    label: {
        fontSize:12,
        marginBottom: 4,
        fontSize: 18,
    },
    input: {
        padding: 6,
        borderRadius: 6,
        fontSize: 18,
        backgroundColor: 'white',
        color:'black'
    
    }

})

/*txt2: {
    textAlign: 'center',
    color: 'black',
    borderRadius: 5,
    backgroundColor: 'white',
    fontSize: 15,
    borderWidth: 0.5,
    marginTop: 0.1,
    margin: 10,
  },*/
# Getting Started with Compiler

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

### <S> -> keyword <idefs> <am> <chmod> class <ID> <inh> { <cbody> } <defs>
### <idefs> -> interface <interfacedef> | access modifier | <DT> | const | conceptual | class

### <am> -> access modifier | <DT> | const | conceptual | class | static | void | interface

### <chmod> -> const | <DT> | conceptual | class

### <inh> -> inherit <ID> | implement | {
### <cbody> -> (Access Modifier | void | static | <DT>) <am> <TM> <cbody1> | <ID> <objdec> <cbody> | conceptual <conceptual> <cbody> | ArrayList <ArrayList> | }
### <defs> -> <am> <defs1> | $
### <defs1> -> (const | conceptual | class) <classdef> <defs> | interface <interfacedef> <defs> | $
### <classdef> -> (const | conceptual | class) <chmod> class ID <inh> <implement> { <Cbody> }
### <cbody1> -> void <ID> <fn> <cbody> | <DT> <Orarr> | }
### <Orarr> -> <ID> <orFn> <Cbody> | [] <ID> <orarrfn> <cbody>
### <orarrfn> -> ( <Fn> | = <arrInit1> ; | , ID <oArrInit> <list2>
### <fn> -> ( <Pl> ) { <Mst> }
### <orFn> -> ( <fn> | = <init> <list> | ; | , <ID> <Init> <List>
### <init> ->  =  <oArrInit> | ; | ,
### <oarrinit> ->  <OE> | <ArrInit1> | , | ;
### <arrInit1> -> { <arrvar> } | new <DT> [ <OE> ]

### <list> -> ; | , <ID>  <INIT>  <list>  
### <fnList> -> ;  |  ,  ID  <INIT>  <fnList>  
### <List2> -> ;  |  ,  ID  <oArrInit>  <List2> 
###  <Arr-var> -> <Oe><Arr-var1>| ∈
### <Arr-var1> -> ,<Oe><Arr-var1>| ∈
### <TM> -> static|∈

## DECLARATION
### <Am> -> pub|pvt|pro|∈
### <DEC> -> <DF> <Arr> <ID> <INIT> <fnLIST>
### <Arr>	[]| ∈
### <DF>	WN  | FLOAT  | BOOL  |TEXT | LETTER

## BODY
### <BODY> -> ;  |  <SST>  |  {  <MST>  }

## Single Line Statement
### <SST> -> <DEC> | <IF-ELSE> | <DO-WHILE> | <WHILE-ST> | <FOR-ST> | <SWITCH-ST> | <INC-DEC> <ID> <X> ; | <ID> <OTHER> | break ; | continue ; | <return> | <throw> | <print> | <ArrayList>

## Mutiline Line Statement
### <MST> -> ∈  |  <SST>  <MST>
### <OTHER> -> <ID> = NEW <ID> ( <PL> ) ; | <Y> ( <PL> ) ; | <X> <OTHER2> ;
### <OTHER2> -> <ASSIGN-OPR> <OE> | <INC-DEC>
### <Y> ->	. <ID> <Y> | [ <OE> ] . <ID> <Y> | ∈
### <X> -> . <ID> <X> | [ <OE> ] . <ID> <X> | [ <OE> ] | ( <PL> ) . id <X> | ∈
### <PL> -> <DF> <AR> <ID> <PL1> | <ID> <AR> <ID> <PL1> | ∈
### <PL1> -> , <P2> | ∈
### <PL2> -> <DF> <AR> <ID> <PL1> | <ID> <AR> ID <PL1>

## FOR
### <FOR-ST> -> FOR ( <C1> <C2> ; <C3> )  <BODY>
### <C1> ->	<DEC> | <ASSIGN_ST> | ;
### <ASSIGN_ST>	-> <ID> <X> <ASSIGN_OPR> <OE> ;
### <C2> -> <OE> | ∈
### <C3> -> <ID> <X> <C3'> | <INC-DEC> <INC-DEC-ST1> | ∈
### <C3'> -> <ASSIGN_OPR> <OE> | <INC-DEC-ST>
### <ASSIGN_OPR> -> =  |  <CMPD-ASS-OPR>
### <INC-DEC-ST> -> <INC-DEC> <INC-DEC-ST1>
### <INC-DEC-ST1> -> <ID> <X> | ∈

## Return
### <return> -> return <return1>
### <return1> -> ; | <OE> <return1>

## IF ELSE
### <IF-ELSE> -> IF ( <OE> ) <BODY> <OELSE>
### <OELSE> -> ∈ | ELSE <BODY>

## INHERIT
### <inh> -> inherit <ID> | ∈

## WHILE
### <WHILE-ST> -> WHILE ( <OE> ) <BODY> 

## DO WHILE
### <DO-WHILE> -> DO { <MST> } WHILE ( <OE> ) ;

## SWITCH CASE
### <SWT_CASE> -> SWITCH ( <OE> ) { <SW_BODY> <DEFAULT> }
### <SW_BODY> -> CASE <ID> : <BODY> break ; <SW_BODY> | ∈
### <DEFAULT> -> default : <Body> | ∈

## CONCEPTUAL
### <CONCEPTUAL> -> conceptual void <ID> ( <PL> ) ;

## INTERFACE
### <interface_def> -> interface <ID> <inh> { <interface-body> }
### <interface-body> ->	<dec> <interface-body> | <CONCEPTUAL> <interface-body> | ∈

## IMPLEMENT
### <implement> -> implement <ID> <implements'> | ∈
### <implements'> -> ∈ | , <ID> <implements'>

## THROW
### <throw> -> throw new Error ( CONST )
### <ArrayList> -> ArrayList <DF> <ID> = new ArrayList <DF> ( ) ;
### <print> -> print ( Const )

## EXPRESSIONS
### <OE> -> <AE> <OE'>
### <OE'> -> OOP <AE> <OE'> | ∈
### <AE> -> <RE> <AE'>
### <AE'> -> AOP <RE> <AE'> | ∈
### <RE> -> <E> <RE'>
### <RE'> -> ROP <E> <RE'> | ∈
### <E> -> <T> <E'>
### <E'> -> PM <T> <E'> | ∈
### <T> ->  <F> <T'>
### <T'> -> MDM <F> <T'> | ∈
### <F> -> <Th> ID <F'>  | <CONST> | ( <OE> ) | INC_DEC ID | ! <F> 
### <F'> ->  Ɛ | (<PL>) | INC_DEC | [ <OE> ] 
### <Th> -> this. | Ɛ 

### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $
### <defs> -> <am> <defs1> | $

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)
